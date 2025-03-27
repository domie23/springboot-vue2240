
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
 * 发布用户
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/fabuyonghu")
public class FabuyonghuController {
    private static final Logger logger = LoggerFactory.getLogger(FabuyonghuController.class);

    private static final String TABLE_NAME = "fabuyonghu";

    @Autowired
    private FabuyonghuService fabuyonghuService;


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
    private RenwuOrderService renwuOrderService;//任务订单
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
        PageUtils page = fabuyonghuService.queryPage(params);

        //字典表数据转换
        List<FabuyonghuView> list =(List<FabuyonghuView>)page.getList();
        for(FabuyonghuView c:list){
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
        FabuyonghuEntity fabuyonghu = fabuyonghuService.selectById(id);
        if(fabuyonghu !=null){
            //entity转view
            FabuyonghuView view = new FabuyonghuView();
            BeanUtils.copyProperties( fabuyonghu , view );//把实体数据重构到view中
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
    public R save(@RequestBody FabuyonghuEntity fabuyonghu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,fabuyonghu:{}",this.getClass().getName(),fabuyonghu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<FabuyonghuEntity> queryWrapper = new EntityWrapper<FabuyonghuEntity>()
            .eq("username", fabuyonghu.getUsername())
            .or()
            .eq("fabuyonghu_phone", fabuyonghu.getFabuyonghuPhone())
            .or()
            .eq("fabuyonghu_id_number", fabuyonghu.getFabuyonghuIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FabuyonghuEntity fabuyonghuEntity = fabuyonghuService.selectOne(queryWrapper);
        if(fabuyonghuEntity==null){
            fabuyonghu.setCreateTime(new Date());
            fabuyonghu.setPassword("123456");
            fabuyonghuService.insert(fabuyonghu);
            return R.ok();
        }else {
            return R.error(511,"账户或者发布用户手机号或者发布用户身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody FabuyonghuEntity fabuyonghu, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,fabuyonghu:{}",this.getClass().getName(),fabuyonghu.toString());
        FabuyonghuEntity oldFabuyonghuEntity = fabuyonghuService.selectById(fabuyonghu.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        if("".equals(fabuyonghu.getFabuyonghuPhoto()) || "null".equals(fabuyonghu.getFabuyonghuPhoto())){
                fabuyonghu.setFabuyonghuPhoto(null);
        }

            fabuyonghuService.updateById(fabuyonghu);//根据id更新
            return R.ok();
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<FabuyonghuEntity> oldFabuyonghuList =fabuyonghuService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        fabuyonghuService.deleteBatchIds(Arrays.asList(ids));

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
            List<FabuyonghuEntity> fabuyonghuList = new ArrayList<>();//上传的东西
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
                            FabuyonghuEntity fabuyonghuEntity = new FabuyonghuEntity();
//                            fabuyonghuEntity.setUsername(data.get(0));                    //账户 要改的
//                            //fabuyonghuEntity.setPassword("123456");//密码
//                            fabuyonghuEntity.setFabuyonghuName(data.get(0));                    //发布用户姓名 要改的
//                            fabuyonghuEntity.setFabuyonghuPhone(data.get(0));                    //发布用户手机号 要改的
//                            fabuyonghuEntity.setFabuyonghuIdNumber(data.get(0));                    //发布用户身份证号 要改的
//                            fabuyonghuEntity.setFabuyonghuPhoto("");//详情和图片
//                            fabuyonghuEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            fabuyonghuEntity.setNewMoney(data.get(0));                    //余额 要改的
//                            fabuyonghuEntity.setFabuyonghuEmail(data.get(0));                    //发布用户邮箱 要改的
//                            fabuyonghuEntity.setCreateTime(date);//时间
                            fabuyonghuList.add(fabuyonghuEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //发布用户手机号
                                if(seachFields.containsKey("fabuyonghuPhone")){
                                    List<String> fabuyonghuPhone = seachFields.get("fabuyonghuPhone");
                                    fabuyonghuPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> fabuyonghuPhone = new ArrayList<>();
                                    fabuyonghuPhone.add(data.get(0));//要改的
                                    seachFields.put("fabuyonghuPhone",fabuyonghuPhone);
                                }
                                //发布用户身份证号
                                if(seachFields.containsKey("fabuyonghuIdNumber")){
                                    List<String> fabuyonghuIdNumber = seachFields.get("fabuyonghuIdNumber");
                                    fabuyonghuIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> fabuyonghuIdNumber = new ArrayList<>();
                                    fabuyonghuIdNumber.add(data.get(0));//要改的
                                    seachFields.put("fabuyonghuIdNumber",fabuyonghuIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<FabuyonghuEntity> fabuyonghuEntities_username = fabuyonghuService.selectList(new EntityWrapper<FabuyonghuEntity>().in("username", seachFields.get("username")));
                        if(fabuyonghuEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FabuyonghuEntity s:fabuyonghuEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //发布用户手机号
                        List<FabuyonghuEntity> fabuyonghuEntities_fabuyonghuPhone = fabuyonghuService.selectList(new EntityWrapper<FabuyonghuEntity>().in("fabuyonghu_phone", seachFields.get("fabuyonghuPhone")));
                        if(fabuyonghuEntities_fabuyonghuPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FabuyonghuEntity s:fabuyonghuEntities_fabuyonghuPhone){
                                repeatFields.add(s.getFabuyonghuPhone());
                            }
                            return R.error(511,"数据库的该表中的 [发布用户手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //发布用户身份证号
                        List<FabuyonghuEntity> fabuyonghuEntities_fabuyonghuIdNumber = fabuyonghuService.selectList(new EntityWrapper<FabuyonghuEntity>().in("fabuyonghu_id_number", seachFields.get("fabuyonghuIdNumber")));
                        if(fabuyonghuEntities_fabuyonghuIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(FabuyonghuEntity s:fabuyonghuEntities_fabuyonghuIdNumber){
                                repeatFields.add(s.getFabuyonghuIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [发布用户身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        fabuyonghuService.insertBatch(fabuyonghuList);
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
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        FabuyonghuEntity fabuyonghu = fabuyonghuService.selectOne(new EntityWrapper<FabuyonghuEntity>().eq("username", username));
        if(fabuyonghu==null || !fabuyonghu.getPassword().equals(password))
            return R.error("账号或密码不正确");
        String token = tokenService.generateToken(fabuyonghu.getId(),username, "fabuyonghu", "发布用户");
        R r = R.ok();
        r.put("token", token);
        r.put("role","发布用户");
        r.put("username",fabuyonghu.getFabuyonghuName());
        r.put("tableName","fabuyonghu");
        r.put("userId",fabuyonghu.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody FabuyonghuEntity fabuyonghu, HttpServletRequest request) {
//    	ValidatorUtils.validateEntity(user);
        Wrapper<FabuyonghuEntity> queryWrapper = new EntityWrapper<FabuyonghuEntity>()
            .eq("username", fabuyonghu.getUsername())
            .or()
            .eq("fabuyonghu_phone", fabuyonghu.getFabuyonghuPhone())
            .or()
            .eq("fabuyonghu_id_number", fabuyonghu.getFabuyonghuIdNumber())
            ;
        FabuyonghuEntity fabuyonghuEntity = fabuyonghuService.selectOne(queryWrapper);
        if(fabuyonghuEntity != null)
            return R.error("账户或者发布用户手机号或者发布用户身份证号已经被使用");
        fabuyonghu.setNewMoney(0.0);
        fabuyonghu.setCreateTime(new Date());
        fabuyonghuService.insert(fabuyonghu);

        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id, HttpServletRequest request) {
        FabuyonghuEntity fabuyonghu = fabuyonghuService.selectById(id);
        fabuyonghu.setPassword("123456");
        fabuyonghuService.updateById(fabuyonghu);
        return R.ok();
    }

	/**
	 * 修改密码
	 */
	@GetMapping(value = "/updatePassword")
	public R updatePassword(String  oldPassword, String  newPassword, HttpServletRequest request) {
        FabuyonghuEntity fabuyonghu = fabuyonghuService.selectById((Integer)request.getSession().getAttribute("userId"));
		if(newPassword == null){
			return R.error("新密码不能为空") ;
		}
		if(!oldPassword.equals(fabuyonghu.getPassword())){
			return R.error("原密码输入错误");
		}
		if(newPassword.equals(fabuyonghu.getPassword())){
			return R.error("新密码不能和原密码一致") ;
		}
        fabuyonghu.setPassword(newPassword);
		fabuyonghuService.updateById(fabuyonghu);
		return R.ok();
	}



    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        FabuyonghuEntity fabuyonghu = fabuyonghuService.selectOne(new EntityWrapper<FabuyonghuEntity>().eq("username", username));
        if(fabuyonghu!=null){
            fabuyonghu.setPassword("123456");
            fabuyonghuService.updateById(fabuyonghu);
            return R.ok();
        }else{
           return R.error("账号不存在");
        }
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrFabuyonghu(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        FabuyonghuEntity fabuyonghu = fabuyonghuService.selectById(id);
        if(fabuyonghu !=null){
            //entity转view
            FabuyonghuView view = new FabuyonghuView();
            BeanUtils.copyProperties( fabuyonghu , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }



    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = fabuyonghuService.queryPage(params);

        //字典表数据转换
        List<FabuyonghuView> list =(List<FabuyonghuView>)page.getList();
        for(FabuyonghuView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        FabuyonghuEntity fabuyonghu = fabuyonghuService.selectById(id);
            if(fabuyonghu !=null){


                //entity转view
                FabuyonghuView view = new FabuyonghuView();
                BeanUtils.copyProperties( fabuyonghu , view );//把实体数据重构到view中

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
    public R add(@RequestBody FabuyonghuEntity fabuyonghu, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,fabuyonghu:{}",this.getClass().getName(),fabuyonghu.toString());
        Wrapper<FabuyonghuEntity> queryWrapper = new EntityWrapper<FabuyonghuEntity>()
            .eq("username", fabuyonghu.getUsername())
            .or()
            .eq("fabuyonghu_phone", fabuyonghu.getFabuyonghuPhone())
            .or()
            .eq("fabuyonghu_id_number", fabuyonghu.getFabuyonghuIdNumber())
//            .notIn("fabuyonghu_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        FabuyonghuEntity fabuyonghuEntity = fabuyonghuService.selectOne(queryWrapper);
        if(fabuyonghuEntity==null){
            fabuyonghu.setCreateTime(new Date());
            fabuyonghu.setPassword("123456");
        fabuyonghuService.insert(fabuyonghu);

            return R.ok();
        }else {
            return R.error(511,"账户或者发布用户手机号或者发布用户身份证号已经被使用");
        }
    }

}

