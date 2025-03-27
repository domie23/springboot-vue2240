import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
    // 解决多次点击左侧菜单报错问题
    const VueRouterPush = VueRouter.prototype.push
    VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err => err)
    }
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
import beifen from '@/views/modules/databaseBackup/beifen'
import huanyuan from '@/views/modules/databaseBackup/huanyuan'

     import users from '@/views/modules/users/list'
    import dictionary from '@/views/modules/dictionary/list'
    import forum from '@/views/modules/forum/list'
    import gonggao from '@/views/modules/gonggao/list'
    import jiequyonghu from '@/views/modules/jiequyonghu/list'
    import renwu from '@/views/modules/renwu/list'
    import renwuChat from '@/views/modules/renwuChat/list'
    import renwuCollection from '@/views/modules/renwuCollection/list'
    import renwuCommentback from '@/views/modules/renwuCommentback/list'
    import renwuOrder from '@/views/modules/renwuOrder/list'
    import fabuyonghu from '@/views/modules/fabuyonghu/list'
    import config from '@/views/modules/config/list'
    import dictionaryForum from '@/views/modules/dictionaryForum/list'
    import dictionaryForumState from '@/views/modules/dictionaryForumState/list'
    import dictionaryGonggao from '@/views/modules/dictionaryGonggao/list'
    import dictionaryRenwu from '@/views/modules/dictionaryRenwu/list'
    import dictionaryRenwuChat from '@/views/modules/dictionaryRenwuChat/list'
    import dictionaryRenwuCollection from '@/views/modules/dictionaryRenwuCollection/list'
    import dictionaryRenwuOrder from '@/views/modules/dictionaryRenwuOrder/list'
    import dictionaryRenwuYesno from '@/views/modules/dictionaryRenwuYesno/list'
    import dictionaryRenwuZhuangtai from '@/views/modules/dictionaryRenwuZhuangtai/list'
    import dictionarySex from '@/views/modules/dictionarySex/list'
    import dictionaryZhuangtai from '@/views/modules/dictionaryZhuangtai/list'





//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '修改密码',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }, {
        path: '/huanyuan',
        name: '数据还原',
        component: huanyuan
    }, {
        path: '/beifen',
        name: '数据备份',
        component: beifen
    }, {
        path: '/users',
        name: '管理信息',
        component: users
    }
    ,{
        path: '/dictionaryForum',
        name: '帖子类型',
        component: dictionaryForum
    }
    ,{
        path: '/dictionaryForumState',
        name: '帖子状态',
        component: dictionaryForumState
    }
    ,{
        path: '/dictionaryGonggao',
        name: '公告类型',
        component: dictionaryGonggao
    }
    ,{
        path: '/dictionaryRenwu',
        name: '任务类型',
        component: dictionaryRenwu
    }
    ,{
        path: '/dictionaryRenwuChat',
        name: '数据类型',
        component: dictionaryRenwuChat
    }
    ,{
        path: '/dictionaryRenwuCollection',
        name: '收藏表类型',
        component: dictionaryRenwuCollection
    }
    ,{
        path: '/dictionaryRenwuOrder',
        name: '订单类型',
        component: dictionaryRenwuOrder
    }
    ,{
        path: '/dictionaryRenwuYesno',
        name: '申请状态',
        component: dictionaryRenwuYesno
    }
    ,{
        path: '/dictionaryRenwuZhuangtai',
        name: '任务状态',
        component: dictionaryRenwuZhuangtai
    }
    ,{
        path: '/dictionarySex',
        name: '性别类型',
        component: dictionarySex
    }
    ,{
        path: '/dictionaryZhuangtai',
        name: '状态',
        component: dictionaryZhuangtai
    }
    ,{
        path: '/config',
        name: '轮播图',
        component: config
    }


    ,{
        path: '/dictionary',
        name: '字典',
        component: dictionary
      }
    ,{
        path: '/forum',
        name: '论坛',
        component: forum
      }
    ,{
        path: '/gonggao',
        name: '任务资讯公告',
        component: gonggao
      }
    ,{
        path: '/jiequyonghu',
        name: '接取用户',
        component: jiequyonghu
      }
    ,{
        path: '/renwu',
        name: '任务',
        component: renwu
      }
    ,{
        path: '/renwuChat',
        name: '任务咨询',
        component: renwuChat
      }
    ,{
        path: '/renwuCollection',
        name: '任务收藏',
        component: renwuCollection
      }
    ,{
        path: '/renwuCommentback',
        name: '任务评价',
        component: renwuCommentback
      }
    ,{
        path: '/renwuOrder',
        name: '任务订单',
        component: renwuOrder
      }
    ,{
        path: '/fabuyonghu',
        name: '发布用户',
        component: fabuyonghu
      }


    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
