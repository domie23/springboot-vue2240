
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
 * 任务咨询
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/renwuChat")
public class RenwuChatController {
    private static final Logger logger = LoggerFactory.getLogger(RenwuChatController.class);

    private static final String TABLE_NAME = "renwuChat";

    @Autowired
    private RenwuChatService renwuChatService;


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
        CommonUtil.checkMap(params);
        PageUtils page = renwuChatService.queryPage(params);

        //字典表数据转换
        List<RenwuChatView> list =(List<RenwuChatView>)page.getList();
        for(RenwuChatView c:list){
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
        RenwuChatEntity renwuChat = renwuChatService.selectById(id);
        if(renwuChat !=null){
            //entity转view
            RenwuChatView view = new RenwuChatView();
            BeanUtils.copyProperties( renwuChat , view );//把实体数据重构到view中
            //级联表 接取用户
            //级联表
            JiequyonghuEntity jiequyonghu = jiequyonghuService.selectById(renwuChat.getJiequyonghuId());
            if(jiequyonghu != null){
            BeanUtils.copyProperties( jiequyonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "jiequyonghuId"
, "fabuyonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setJiequyonghuId(jiequyonghu.getId());
            }
            //级联表 发布用户
            //级联表
            FabuyonghuEntity fabuyonghu = fabuyonghuService.selectById(renwuChat.getFabuyonghuId());
            if(fabuyonghu != null){
            BeanUtils.copyProperties( fabuyonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "jiequyonghuId"
, "fabuyonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
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
    public R save(@RequestBody RenwuChatEntity renwuChat, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,renwuChat:{}",this.getClass().getName(),renwuChat.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("接取用户".equals(role))
            renwuChat.setJiequyonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("发布用户".equals(role))
            renwuChat.setFabuyonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<RenwuChatEntity> queryWrapper = new EntityWrapper<RenwuChatEntity>()
            .eq("jiequyonghu_id", renwuChat.getJiequyonghuId())
            .eq("fabuyonghu_id", renwuChat.getFabuyonghuId())
            .eq("zhuangtai_types", renwuChat.getZhuangtaiTypes())
            .eq("renwu_chat_types", renwuChat.getRenwuChatTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        RenwuChatEntity renwuChatEntity = renwuChatService.selectOne(queryWrapper);
        if(renwuChatEntity==null){
            renwuChat.setInsertTime(new Date());
            renwuChat.setCreateTime(new Date());
            renwuChatService.insert(renwuChat);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody RenwuChatEntity renwuChat, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,renwuChat:{}",this.getClass().getName(),renwuChat.toString());
        RenwuChatEntity oldRenwuChatEntity = renwuChatService.selectById(renwuChat.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("接取用户".equals(role))
//            renwuChat.setJiequyonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
//        else if("发布用户".equals(role))
//            renwuChat.setFabuyonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

            renwuChatService.updateById(renwuChat);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<RenwuChatEntity> oldRenwuChatList =renwuChatService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        renwuChatService.deleteBatchIds(Arrays.asList(ids));

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
            List<RenwuChatEntity> renwuChatList = new ArrayList<>();//上传的东西
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
                            RenwuChatEntity renwuChatEntity = new RenwuChatEntity();
//                            renwuChatEntity.setJiequyonghuId(Integer.valueOf(data.get(0)));   //提问人 要改的
//                            renwuChatEntity.setFabuyonghuId(Integer.valueOf(data.get(0)));   //回答人 要改的
//                            renwuChatEntity.setRenwuChatIssueText(data.get(0));                    //问题 要改的
//                            renwuChatEntity.setIssueTime(sdf.parse(data.get(0)));          //问题时间 要改的
//                            renwuChatEntity.setRenwuChatReplyText(data.get(0));                    //回复 要改的
//                            renwuChatEntity.setReplyTime(sdf.parse(data.get(0)));          //回复时间 要改的
//                            renwuChatEntity.setZhuangtaiTypes(Integer.valueOf(data.get(0)));   //状态 要改的
//                            renwuChatEntity.setRenwuChatTypes(Integer.valueOf(data.get(0)));   //数据类型 要改的
//                            renwuChatEntity.setInsertTime(date);//时间
//                            renwuChatEntity.setCreateTime(date);//时间
                            renwuChatList.add(renwuChatEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        renwuChatService.insertBatch(renwuChatList);
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
        PageUtils page = renwuChatService.queryPage(params);

        //字典表数据转换
        List<RenwuChatView> list =(List<RenwuChatView>)page.getList();
        for(RenwuChatView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        RenwuChatEntity renwuChat = renwuChatService.selectById(id);
            if(renwuChat !=null){


                //entity转view
                RenwuChatView view = new RenwuChatView();
                BeanUtils.copyProperties( renwuChat , view );//把实体数据重构到view中

                //级联表
                    JiequyonghuEntity jiequyonghu = jiequyonghuService.selectById(renwuChat.getJiequyonghuId());
                if(jiequyonghu != null){
                    BeanUtils.copyProperties( jiequyonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setJiequyonghuId(jiequyonghu.getId());
                }
                //级联表
                    FabuyonghuEntity fabuyonghu = fabuyonghuService.selectById(renwuChat.getFabuyonghuId());
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
    public R add(@RequestBody RenwuChatEntity renwuChat, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,renwuChat:{}",this.getClass().getName(),renwuChat.toString());
        Wrapper<RenwuChatEntity> queryWrapper = new EntityWrapper<RenwuChatEntity>()
            .eq("jiequyonghu_id", renwuChat.getJiequyonghuId())
            .eq("fabuyonghu_id", renwuChat.getFabuyonghuId())
            .eq("renwu_chat_issue_text", renwuChat.getRenwuChatIssueText())
            .eq("renwu_chat_reply_text", renwuChat.getRenwuChatReplyText())
            .eq("zhuangtai_types", renwuChat.getZhuangtaiTypes())
            .eq("renwu_chat_types", renwuChat.getRenwuChatTypes())
//            .notIn("renwu_chat_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        RenwuChatEntity renwuChatEntity = renwuChatService.selectOne(queryWrapper);
        if(renwuChatEntity==null){
            renwuChat.setInsertTime(new Date());
            renwuChat.setCreateTime(new Date());
        renwuChatService.insert(renwuChat);

            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

}

