
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
 * 任务订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/renwuOrder")
public class RenwuOrderController {
    private static final Logger logger = LoggerFactory.getLogger(RenwuOrderController.class);

    private static final String TABLE_NAME = "renwuOrder";

    @Autowired
    private RenwuOrderService renwuOrderService;


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
    private RenwuService renwuService;//任务
    @Autowired
    private RenwuChatService renwuChatService;//任务咨询
    @Autowired
    private RenwuCollectionService renwuCollectionService;//任务收藏
    @Autowired
    private RenwuCommentbackService renwuCommentbackService;//任务评价
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
        CommonUtil.checkMap(params);
        PageUtils page = renwuOrderService.queryPage(params);

        //字典表数据转换
        List<RenwuOrderView> list =(List<RenwuOrderView>)page.getList();
        for(RenwuOrderView c:list){
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
        RenwuOrderEntity renwuOrder = renwuOrderService.selectById(id);
        if(renwuOrder !=null){
            //entity转view
            RenwuOrderView view = new RenwuOrderView();
            BeanUtils.copyProperties( renwuOrder , view );//把实体数据重构到view中
            //级联表 任务
            //级联表
            RenwuEntity renwu = renwuService.selectById(renwuOrder.getRenwuId());
            if(renwu != null){
            BeanUtils.copyProperties( renwu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "jiequyonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setRenwuId(renwu.getId());
            }
            //级联表 接取用户
            //级联表
            JiequyonghuEntity jiequyonghu = jiequyonghuService.selectById(renwuOrder.getJiequyonghuId());
            if(jiequyonghu != null){
            BeanUtils.copyProperties( jiequyonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "jiequyonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setJiequyonghuId(jiequyonghu.getId());
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
    public R save(@RequestBody RenwuOrderEntity renwuOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,renwuOrder:{}",this.getClass().getName(),renwuOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("接取用户".equals(role))
            renwuOrder.setJiequyonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        renwuOrder.setCreateTime(new Date());
        renwuOrder.setInsertTime(new Date());
        renwuOrderService.insert(renwuOrder);

        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody RenwuOrderEntity renwuOrder, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,renwuOrder:{}",this.getClass().getName(),renwuOrder.toString());
        RenwuOrderEntity oldRenwuOrderEntity = renwuOrderService.selectById(renwuOrder.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("接取用户".equals(role))
//            renwuOrder.setJiequyonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            renwuOrderService.updateById(renwuOrder);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<RenwuOrderEntity> oldRenwuOrderList =renwuOrderService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        renwuOrderService.deleteBatchIds(Arrays.asList(ids));

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
            List<RenwuOrderEntity> renwuOrderList = new ArrayList<>();//上传的东西
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
                            RenwuOrderEntity renwuOrderEntity = new RenwuOrderEntity();
//                            renwuOrderEntity.setRenwuOrderUuidNumber(data.get(0));                    //订单编号 要改的
//                            renwuOrderEntity.setRenwuId(Integer.valueOf(data.get(0)));   //任务 要改的
//                            renwuOrderEntity.setJiequyonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            renwuOrderEntity.setRenwuOrderTruePrice(data.get(0));                    //悬赏金额 要改的
//                            renwuOrderEntity.setRenwuOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            renwuOrderEntity.setInsertTime(date);//时间
//                            renwuOrderEntity.setCreateTime(date);//时间
                            renwuOrderList.add(renwuOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单编号
                                if(seachFields.containsKey("renwuOrderUuidNumber")){
                                    List<String> renwuOrderUuidNumber = seachFields.get("renwuOrderUuidNumber");
                                    renwuOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> renwuOrderUuidNumber = new ArrayList<>();
                                    renwuOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("renwuOrderUuidNumber",renwuOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单编号
                        List<RenwuOrderEntity> renwuOrderEntities_renwuOrderUuidNumber = renwuOrderService.selectList(new EntityWrapper<RenwuOrderEntity>().in("renwu_order_uuid_number", seachFields.get("renwuOrderUuidNumber")));
                        if(renwuOrderEntities_renwuOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(RenwuOrderEntity s:renwuOrderEntities_renwuOrderUuidNumber){
                                repeatFields.add(s.getRenwuOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        renwuOrderService.insertBatch(renwuOrderList);
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
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = renwuOrderService.queryPage(params);

        //字典表数据转换
        List<RenwuOrderView> list =(List<RenwuOrderView>)page.getList();
        for(RenwuOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        RenwuOrderEntity renwuOrder = renwuOrderService.selectById(id);
            if(renwuOrder !=null){


                //entity转view
                RenwuOrderView view = new RenwuOrderView();
                BeanUtils.copyProperties( renwuOrder , view );//把实体数据重构到view中

                //级联表
                    RenwuEntity renwu = renwuService.selectById(renwuOrder.getRenwuId());
                if(renwu != null){
                    BeanUtils.copyProperties( renwu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setRenwuId(renwu.getId());
                }
                //级联表
                    JiequyonghuEntity jiequyonghu = jiequyonghuService.selectById(renwuOrder.getJiequyonghuId());
                if(jiequyonghu != null){
                    BeanUtils.copyProperties( jiequyonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJiequyonghuId(jiequyonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 任务接取
    */
    @RequestMapping("/add")
    public R add(@RequestBody RenwuOrderEntity renwuOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,renwuOrder:{}",this.getClass().getName(),renwuOrder.toString());
            RenwuEntity renwuEntity = renwuService.selectById(renwuOrder.getRenwuId());
            if(renwuEntity == null){
                return R.error(511,"查不到该任务");
            }
        RenwuOrderEntity renwuOrderEntity = renwuOrderService.selectOne(new EntityWrapper<RenwuOrderEntity>()
                .notIn("renwu_order_types", new Integer[]{102,107})
                .eq("renwu_id", renwuOrder.getRenwuId())
        );
            if(renwuOrderEntity != null){
                return R.error("该用户已经接取了该任务,请去任务订单中核实");
            }


        //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            renwuOrder.setRenwuOrderTypes(101); //设置订单状态为已申请领取
            renwuOrder.setRenwuOrderTruePrice(renwuEntity.getRenwuJine()); //设置实付价格
            renwuOrder.setJiequyonghuId(userId); //设置订单支付人id
            renwuOrder.setRenwuOrderUuidNumber(String.valueOf(new Date().getTime()));
            renwuOrder.setInsertTime(new Date());
            renwuOrder.setCreateTime(new Date());
                renwuOrderService.insert(renwuOrder);//新增订单

            return R.ok();
    }


    /**
    * 取消领取
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            RenwuOrderEntity renwuOrder = renwuOrderService.selectById(id);//当前表service
            Integer renwuId = renwuOrder.getRenwuId();
            if(renwuId == null)
                return R.error(511,"查不到该任务");
            RenwuEntity renwuEntity = renwuService.selectById(renwuId);
            if(renwuEntity == null)
                return R.error(511,"查不到该任务");







            renwuOrder.setRenwuOrderTypes(102);//设置订单状态为已取消领取
            renwuOrderService.updateById(renwuOrder);//根据id更新
            renwuService.updateById(renwuEntity);//更新订单中任务的信息

            return R.ok();
    }

    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer renwuCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            RenwuOrderEntity renwuOrder = renwuOrderService.selectById(id);
        if(renwuOrder == null)
            return R.error(511,"查不到该订单");
        Integer renwuId = renwuOrder.getRenwuId();
        if(renwuId == null)
            return R.error(511,"查不到该任务");

        RenwuCommentbackEntity renwuCommentbackEntity = new RenwuCommentbackEntity();
            renwuCommentbackEntity.setId(id);
            renwuCommentbackEntity.setRenwuId(renwuId);
            renwuCommentbackEntity.setJiequyonghuId((Integer) request.getSession().getAttribute("userId"));
            renwuCommentbackEntity.setRenwuCommentbackPingfenNumber(renwuCommentbackPingfenNumber);
            renwuCommentbackEntity.setRenwuCommentbackText(commentbackText);
            renwuCommentbackEntity.setInsertTime(new Date());
            renwuCommentbackEntity.setReplyText(null);
            renwuCommentbackEntity.setUpdateTime(null);
            renwuCommentbackEntity.setCreateTime(new Date());
            renwuCommentbackService.insert(renwuCommentbackEntity);

            renwuOrder.setRenwuOrderTypes(105);//设置订单状态为已评价
            renwuOrderService.updateById(renwuOrder);//根据id更新
            return R.ok();
    }

    /**
     * 同意领取
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());


        RenwuOrderEntity  renwuOrderEntity = renwuOrderService.selectById(id);





        renwuOrderEntity.setRenwuOrderTypes(103);//设置订单状态为已同意领取
        renwuOrderService.updateById( renwuOrderEntity);

        RenwuEntity renwuEntity = renwuService.selectById(renwuOrderEntity.getRenwuId());
        if(renwuEntity.getRenwuZhuangtaiTypes()!=1){
            return R.error("该任务已被接取");
        }
        renwuEntity.setRenwuZhuangtaiTypes(2);
        renwuService.updateById(renwuEntity);



        return R.ok();
    }

    /**
     * 已完成
     */
    @RequestMapping("/yiwancheng")
    public R yiwancheng(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        RenwuOrderEntity  renwuOrderEntity = renwuOrderService.selectById(id);
        renwuOrderEntity.setRenwuOrderTypes(106);//设置订单状态为已完成
        renwuOrderService.updateById( renwuOrderEntity);



        List<RenwuOrderEntity> list = renwuOrderService.selectList(new EntityWrapper<RenwuOrderEntity>()
            .eq("jiequyonghu_id", renwuOrderEntity.getJiequyonghuId())
        );
        Integer wanchengNum=0;
        Integer wanchengheshibaiNum=0;
        for(RenwuOrderEntity r:list){
            if(r.getRenwuOrderTypes()==106){
                wanchengNum=wanchengNum+1;
            }
            if(r.getRenwuOrderTypes()==106 || r.getRenwuOrderTypes()==107){
                wanchengheshibaiNum=wanchengheshibaiNum+1;
            }
        }

        JiequyonghuEntity jiequyonghuEntity = jiequyonghuService.selectById(renwuOrderEntity.getJiequyonghuId());
        int wanchenglv = new BigDecimal(wanchengNum).divide(new BigDecimal(wanchengheshibaiNum)).multiply(new BigDecimal(100.0)).intValue();
        jiequyonghuEntity.setWanchenglv(wanchenglv);
        jiequyonghuEntity.setNewMoney(jiequyonghuEntity.getNewMoney()+renwuOrderEntity.getRenwuOrderTruePrice());
        jiequyonghuService.updateById(jiequyonghuEntity);

        RenwuEntity renwuEntity = renwuService.selectById(renwuOrderEntity.getRenwuId());
        renwuEntity.setRenwuZhuangtaiTypes(3);
        renwuService.updateById(renwuEntity);


        return R.ok();
    }


    /**
     * 放弃领取
     */
    @RequestMapping("/fangqilingqu")
    public R fangqilingqu(Integer id  , HttpServletRequest request){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        RenwuOrderEntity  renwuOrderEntity = renwuOrderService.selectById(id);
        renwuOrderEntity.setRenwuOrderTypes(107);//设置订单状态为已放弃领取
        renwuOrderService.updateById( renwuOrderEntity);


        List<RenwuOrderEntity> list = renwuOrderService.selectList(new EntityWrapper<RenwuOrderEntity>()
                .eq("jiequyonghu_id", renwuOrderEntity.getJiequyonghuId())
        );
        Integer wanchengNum=0;
        Integer wanchengheshibaiNum=0;
        for(RenwuOrderEntity r:list){
            if(r.getRenwuOrderTypes()==106){
                wanchengNum=wanchengNum+1;
            }
            if(r.getRenwuOrderTypes()==106 || r.getRenwuOrderTypes()==107){
                wanchengheshibaiNum=wanchengheshibaiNum+1;
            }
        }

        JiequyonghuEntity jiequyonghuEntity = jiequyonghuService.selectById(renwuOrderEntity.getJiequyonghuId());
        int wanchenglv = new BigDecimal(wanchengNum).divide(new BigDecimal(wanchengheshibaiNum)).multiply(new BigDecimal(100.0)).intValue();
        jiequyonghuEntity.setWanchenglv(wanchenglv);
        jiequyonghuService.updateById(jiequyonghuEntity);


        RenwuEntity renwuEntity = renwuService.selectById(renwuOrderEntity.getRenwuId());
        renwuEntity.setRenwuZhuangtaiTypes(1);
        renwuService.updateById(renwuEntity);


        return R.ok();
    }


}

