
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 任务
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/renwu")
public class RenwuController {
    private static final Logger logger = LoggerFactory.getLogger(RenwuController.class);

    private static final String TABLE_NAME = "renwu";

    @Autowired
    private RenwuService renwuService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//论坛
    @Autowired
    private GonggaoService gonggaoService;//任务资讯公告
    @Autowired
    private JiequyonghuService jiequyonghuService;//接取用户
    @Autowired
    private RenwuChatService renwuChatService;//任务咨询
    @Autowired
    private RenwuCollectionService renwuCollectionService;//任务收藏
    @Autowired
    private RenwuCommentbackService renwuCommentbackService;//任务评价
    @Autowired
    private RenwuOrderService renwuOrderService;//任务订单
    @Autowired
    private FabuyonghuService fabuyonghuService;//发布用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("接取用户".equals(role))
            params.put("jiequyonghuId",request.getSession().getAttribute("userId"));
        else if("发布用户".equals(role))
            params.put("fabuyonghuId",request.getSession().getAttribute("userId"));
        params.put("renwuDeleteStart",1);params.put("renwuDeleteEnd",1);
        CommonUtil.checkMap(params);
        PageUtils page = renwuService.queryPage(params);

        //字典表数据转换
        List<RenwuView> list =(List<RenwuView>)page.getList();
        for(RenwuView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        RenwuEntity renwu = renwuService.selectById(id);
        if(renwu !=null){
            //entity转view
            RenwuView view = new RenwuView();
            BeanUtils.copyProperties( renwu , view );//把实体数据重构到view中
            //级联表 发布用户
            //级联表
            FabuyonghuEntity fabuyonghu = fabuyonghuService.selectById(renwu.getFabuyonghuId());
            if(fabuyonghu != null){
            BeanUtils.copyProperties( fabuyonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "fabuyonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setFabuyonghuId(fabuyonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody RenwuEntity renwu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,renwu:{}",this.getClass().getName(),renwu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("发布用户".equals(role)){
            renwu.setFabuyonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
            renwu.setRenwuZhuangtaiTypes(1);
        }

        Wrapper<RenwuEntity> queryWrapper = new EntityWrapper<RenwuEntity>()
                .eq("fabuyonghu_id", renwu.getFabuyonghuId())
                .eq("renwu_name", renwu.getRenwuName())
                .eq("renwu_address", renwu.getRenwuAddress())
                .eq("renwu_types", renwu.getRenwuTypes())
                .eq("renwu_zhuangtai_types", renwu.getRenwuZhuangtaiTypes())
                .eq("renwu_delete", renwu.getRenwuDelete())
                ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        RenwuEntity renwuEntity = renwuService.selectOne(queryWrapper);
        if(renwuEntity==null){


            FabuyonghuEntity fabuyonghuEntity = fabuyonghuService.selectById(renwu.getFabuyonghuId());
            double balance = fabuyonghuEntity.getNewMoney() - renwu.getRenwuJine();
            if(balance<0){
                return R.error("当前发布用户账户余额不够,请充值后再发布任务");
            }
            fabuyonghuEntity.setNewMoney(balance);
            fabuyonghuService.updateById(fabuyonghuEntity);

            renwu.setRenwuYesnoTypes(1);
            renwu.setRenwuClicknum(1);
            renwu.setRenwuDelete(1);
            renwu.setInsertTime(new Date());
            renwu.setCreateTime(new Date());
            renwuService.insert(renwu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody RenwuEntity renwu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,renwu:{}",this.getClass().getName(),renwu.toString());
        RenwuEntity oldRenwuEntity = renwuService.selectById(renwu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("发布用户".equals(role))
//            renwu.setFabuyonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(renwu.getRenwuPhoto()) || "null".equals(renwu.getRenwuPhoto())){
                renwu.setRenwuPhoto(null);
        }
        if("".equals(renwu.getRenwuFile()) || "null".equals(renwu.getRenwuFile())){
                renwu.setRenwuFile(null);
        }

            renwuService.updateById(renwu);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody RenwuEntity renwuEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,renwuEntity:{}",this.getClass().getName(),renwuEntity.toString());

        RenwuEntity oldRenwu = renwuService.selectById(renwuEntity.getId());//查询原先数据

//        if(renwuEntity.getRenwuYesnoTypes() == 2){//通过
//            renwuEntity.setRenwuTypes();
//        }else if(renwuEntity.getRenwuYesnoTypes() == 3){//拒绝
//            renwuEntity.setRenwuTypes();
//        }
        renwuEntity.setRenwuShenheTime(new Date());//审核时间
        renwuService.updateById(renwuEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<RenwuEntity> oldRenwuList =renwuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        ArrayList<RenwuEntity> list = new ArrayList<>();
        for(Integer id:ids){
            RenwuEntity renwuEntity = new RenwuEntity();
            renwuEntity.setId(id);
            renwuEntity.setRenwuDelete(2);
            list.add(renwuEntity);
        }
        if(list != null && list.size() >0){
            renwuService.updateBatchById(list);
        }

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer jiequyonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<RenwuEntity> renwuList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            RenwuEntity renwuEntity = new RenwuEntity();
//                            renwuEntity.setFabuyonghuId(Integer.valueOf(data.get(0)));   //发布用户 要改的
//                            renwuEntity.setRenwuName(data.get(0));                    //任务名称 要改的
//                            renwuEntity.setRenwuUuidNumber(data.get(0));                    //任务编号 要改的
//                            renwuEntity.setRenwuPhoto("");//详情和图片
//                            renwuEntity.setRenwuFile(data.get(0));                    //任务附件 要改的
//                            renwuEntity.setZhixingTime(sdf.parse(data.get(0)));          //任务执行时间 要改的
//                            renwuEntity.setRenwuAddress(data.get(0));                    //执行地点 要改的
//                            renwuEntity.setRenwuTypes(Integer.valueOf(data.get(0)));   //任务类型 要改的
//                            renwuEntity.setRenwuJine(data.get(0));                    //悬赏金额 要改的
//                            renwuEntity.setRenwuClicknum(Integer.valueOf(data.get(0)));   //任务热度 要改的
//                            renwuEntity.setRenwuContent("");//详情和图片
//                            renwuEntity.setRenwuZhuangtaiTypes(Integer.valueOf(data.get(0)));   //任务状态 要改的
//                            renwuEntity.setRenwuDelete(1);//逻辑删除字段
//                            renwuEntity.setInsertTime(date);//时间
//                            renwuEntity.setRenwuYesnoTypes(Integer.valueOf(data.get(0)));   //申请状态 要改的
//                            renwuEntity.setRenwuYesnoText(data.get(0));                    //审核意见 要改的
//                            renwuEntity.setRenwuShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            renwuEntity.setCreateTime(date);//时间
                            renwuList.add(renwuEntity);


                            //把要查询是否重复的字段放入map中
                                //任务编号
                                if(seachFields.containsKey("renwuUuidNumber")){
                                    List<String> renwuUuidNumber = seachFields.get("renwuUuidNumber");
                                    renwuUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> renwuUuidNumber = new ArrayList<>();
                                    renwuUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("renwuUuidNumber",renwuUuidNumber);
                                }
                        }

                        //查询是否重复
                         //任务编号
                        List<RenwuEntity> renwuEntities_renwuUuidNumber = renwuService.selectList(new EntityWrapper<RenwuEntity>().in("renwu_uuid_number", seachFields.get("renwuUuidNumber")).eq("renwu_delete", 1));
                        if(renwuEntities_renwuUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(RenwuEntity s:renwuEntities_renwuUuidNumber){
                                repeatFields.add(s.getRenwuUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [任务编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        renwuService.insertBatch(renwuList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }



    /**
    * 个性推荐
    */
    @IgnoreAuth
    @RequestMapping("/gexingtuijian")
    public R gexingtuijian(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("gexingtuijian方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        CommonUtil.checkMap(params);
        List<RenwuView> returnRenwuViewList = new ArrayList<>();

        //查询订单
        Map<String, Object> params1 = new HashMap<>(params);params1.put("sort","id");params1.put("jiequyonghuId",request.getSession().getAttribute("userId"));
        PageUtils pageUtils = renwuOrderService.queryPage(params1);
        List<RenwuOrderView> orderViewsList =(List<RenwuOrderView>)pageUtils.getList();
        Map<Integer,Integer> typeMap=new HashMap<>();//购买的类型list
        for(RenwuOrderView orderView:orderViewsList){
            Integer renwuTypes = orderView.getRenwuTypes();
            if(typeMap.containsKey(renwuTypes)){
                typeMap.put(renwuTypes,typeMap.get(renwuTypes)+1);
            }else{
                typeMap.put(renwuTypes,1);
            }
        }
        List<Integer> typeList = new ArrayList<>();//排序后的有序的类型 按最多到最少
        typeMap.entrySet().stream().sorted((o1, o2) -> o2.getValue() - o1.getValue()).forEach(e -> typeList.add(e.getKey()));//排序
        Integer limit = Integer.valueOf(String.valueOf(params.get("limit")));
        for(Integer type:typeList){
            Map<String, Object> params2 = new HashMap<>(params);params2.put("renwuTypes",type);
            PageUtils pageUtils1 = renwuService.queryPage(params2);
            List<RenwuView> renwuViewList =(List<RenwuView>)pageUtils1.getList();
            returnRenwuViewList.addAll(renwuViewList);
            if(returnRenwuViewList.size()>= limit) break;//返回的推荐数量大于要的数量 跳出循环
        }
        //正常查询出来商品,用于补全推荐缺少的数据
        PageUtils page = renwuService.queryPage(params);
        if(returnRenwuViewList.size()<limit){//返回数量还是小于要求数量
            int toAddNum = limit - returnRenwuViewList.size();//要添加的数量
            List<RenwuView> renwuViewList =(List<RenwuView>)page.getList();
            for(RenwuView renwuView:renwuViewList){
                Boolean addFlag = true;
                for(RenwuView returnRenwuView:returnRenwuViewList){
                    if(returnRenwuView.getId().intValue() ==renwuView.getId().intValue()) addFlag=false;//返回的数据中已存在此商品
                }
                if(addFlag){
                    toAddNum=toAddNum-1;
                    returnRenwuViewList.add(renwuView);
                    if(toAddNum==0) break;//够数量了
                }
            }
        }else {
            returnRenwuViewList = returnRenwuViewList.subList(0, limit);
        }

        for(RenwuView c:returnRenwuViewList)
            dictionaryService.dictionaryConvert(c, request);
        page.setList(returnRenwuViewList);
        return R.ok().put("data", page);
    }

    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = renwuService.queryPage(params);

        //字典表数据转换
        List<RenwuView> list =(List<RenwuView>)page.getList();
        for(RenwuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        RenwuEntity renwu = renwuService.selectById(id);
            if(renwu !=null){

                //点击数量加1
                renwu.setRenwuClicknum(renwu.getRenwuClicknum()+1);
                renwuService.updateById(renwu);

                //entity转view
                RenwuView view = new RenwuView();
                BeanUtils.copyProperties( renwu , view );//把实体数据重构到view中

                //级联表
                    FabuyonghuEntity fabuyonghu = fabuyonghuService.selectById(renwu.getFabuyonghuId());
                if(fabuyonghu != null){
                    BeanUtils.copyProperties( fabuyonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setFabuyonghuId(fabuyonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody RenwuEntity renwu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,renwu:{}",this.getClass().getName(),renwu.toString());
        Wrapper<RenwuEntity> queryWrapper = new EntityWrapper<RenwuEntity>()
            .eq("fabuyonghu_id", renwu.getFabuyonghuId())
            .eq("renwu_name", renwu.getRenwuName())
            .eq("renwu_uuid_number", renwu.getRenwuUuidNumber())
            .eq("renwu_address", renwu.getRenwuAddress())
            .eq("renwu_types", renwu.getRenwuTypes())
            .eq("renwu_clicknum", renwu.getRenwuClicknum())
            .eq("renwu_zhuangtai_types", renwu.getRenwuZhuangtaiTypes())
            .eq("renwu_delete", renwu.getRenwuDelete())
            .in("renwu_yesno_types", new Integer[]{1,2})
            .eq("renwu_yesno_text", renwu.getRenwuYesnoText())
//            .notIn("renwu_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        RenwuEntity renwuEntity = renwuService.selectOne(queryWrapper);
        if(renwuEntity==null){
            renwu.setRenwuClicknum(1);
            renwu.setRenwuDelete(1);
            renwu.setInsertTime(new Date());
            renwu.setRenwuYesnoTypes(1);
            renwu.setCreateTime(new Date());
        renwuService.insert(renwu);

            return R.ok();
        }else {
            if(renwuEntity.getRenwuYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(renwuEntity.getRenwuYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

