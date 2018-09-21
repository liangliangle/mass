import Main from '@/views/layout/Main'

// 不作为Main组件的子页面展示的页面
export const loginRouter = {
  path: '/login',
  name: '登录',
  component: resolve => {
    require(['@/views/Login'], resolve)
  },
  meta: {
    isLogin: false,
    isAuth: false
  }
}
export const page404Router = {
  path: '404',
  name: '404',
  component: resolve => {
    require(['@/views/404'], resolve)
  },
  meta: {
    isLogin: false,
    isAuth: false
  }
}

// 作为Main组件的子页面展示的页面
export const appRouter = [
  {
    path: '/activity',
    name: '活动中心',
    component: Main,
    children: [{
      path: 'lately',
      name: '最近活动',
      component: resolve => {
        require(['@/views/activity/lately'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'query',
      name: '活动查询',
      component: resolve => {
        require(['@/views/activity/all'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    },
      {
        path: 'add',
        name: '申请活动',
        component: resolve => {
          require(['@/views/activity/add'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      },
      {
        path: 'audit',
        name: '审核活动',
        component: resolve => {
          require(['@/views/activity/audit'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      },
      {
        path: 'detail/:id',
        name: '详情',
        component: resolve => {
          require(['@/views/activity/detail'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      },
      {
        path: 'auditdetail/:id',
        name: '审核详情',
        component: resolve => {
          require(['@/views/activity/auditdetail'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      },
      {
        path: 'mymass',
        name: '我的申请',
        component: resolve => {
          require(['@/views/activity/mymass'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      },
      {
        path: 'edit/:id',
        name: '编辑活动',
        component: resolve => {
          require(['@/views/activity/add'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      }
    ]
  },
  {
    path: '/fina',
    name: '财务中心',
    component: Main,
    children: [{
      path: 'audit',
      name: '审核财务',
      component: resolve => {
        require(['@/views/fina/audit'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'auditdetail/:id',
      name: '审核财务详情',
      component: resolve => {
        require(['@/views/fina/auditdetail'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'detail/:id',
      name: '财务详情',
      component: resolve => {
        require(['@/views/fina/detail'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'edit/:id',
      name: '修改',
      component: resolve => {
        require(['@/views/fina/add'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'add',
      name: '申请',
      component: resolve => {
        require(['@/views/fina/add'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    },
      {
        path: 'mymass',
        name: '社团财务',
        component: resolve => {
          require(['@/views/fina/mymass'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      },
      {
        path: 'query',
        name: '财务查询',
        component: resolve => {
          require(['@/views/fina/query'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      }
    ]
  },
  {
    path: '/system',
    name: '系统设置',
    component: Main,
    children: [{
      path: 'role',
      name: '角色',
      component: resolve => {
        require(['@/views/system/Role'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    },
      {
        path: 'roledetile/:id',
        name: '角色详情',
        component: resolve => {
          require(['@/views/system/roledetile'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      }, {
        path: 'user',
        name: '用户管理',
        component: resolve => {
          require(['@/views/system/User'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      },
      {
        path: 'mass',
        name: '社长管理',
        component: resolve => {
          require(['@/views/system/mass'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      }, {
        path: 'classes',
        name: '班级管理',
        component: resolve => {
          require(['@/views/system/classes'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      }
    ]
  },
  {
    path: '/program',
    name: '节目管理',
    component: Main,
    children: [{
      path: 'adds/:id',
      name: '社团列表',
      component: resolve => {
        require(['@/views/program/adds'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'list/:id',
      name: '活动节目列表',
      component: resolve => {
        require(['@/views/program/list'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }]
  },
  {
    path: '/massed',
    name: '社团管理',
    component: Main,
    children: [{
      path: 'create',
      name: '添加节目',
      component: resolve => {
        require(['@/views/massed/create'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'mycreate',
      name: '我的申请',
      component: resolve => {
        require(['@/views/massed/mycreate'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'audit',
      name: '我的申请',
      component: resolve => {
        require(['@/views/massed/audit'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'see',
      name: '社团列表',
      component: resolve => {
        require(['@/views/massed/query'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'my',
      name: '我的社团',
      component: resolve => {
        require(['@/views/massed/my'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }]
  },
  {
    path: '/vote',
    name: '投票中心',
    component: Main,
    children: [{
      path: 'create',
      name: '添加投票',
      component: resolve => {
        require(['@/views/vote/create'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'voted',
      name: '我的投票',
      component: resolve => {
        require(['@/views/vote/voted'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'see',
      name: '我的创建',
      component: resolve => {
        require(['@/views/vote/mycreate'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'query',
      name: '查询投票',
      component: resolve => {
        require(['@/views/vote/query'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'detail/:id',
      name: '投票详情',
      component: resolve => {
        require(['@/views/vote/detail'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'result/:id',
      name: '投票汇总',
      component: resolve => {
        require(['@/views/vote/result'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }]
  },
  {
    path: '/notice',
    name: '通知中心',
    component: Main,
    children: [{
      path: 'create',
      name: '创建通知',
      component: resolve => {
        require(['@/views/notice/create'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'mycreate',
      name: '我的创建',
      component: resolve => {
        require(['@/views/notice/mycreate'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'my',
      name: '我的通知',
      component: resolve => {
        require(['@/views/notice/my'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'query',
      name: '查询通知',
      component: resolve => {
        require(['@/views/notice/query'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'detile/:id',
      name: '通知详情',
      component: resolve => {
        require(['@/views/notice/detile'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'result/:id',
      name: '通知结果',
      component: resolve => {
        require(['@/views/notice/result'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }
    ]
  },
  {
    path: '/sign',
    name: '签到中心',
    component: Main,
    children: [{
      path: 'create',
      name: '创建签到',
      component: resolve => {
        require(['@/views/sign/create'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'mycreate',
      name: '我的创建',
      component: resolve => {
        require(['@/views/sign/mycreate'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'result/:id',
      name: '签到结果',
      component: resolve => {
        require(['@/views/sign/result'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'signed',
      name: '补签',
      component: resolve => {
        require(['@/views/sign/signed'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'slist',
      name: '签到列表',
      component: resolve => {
        require(['@/views/sign/query'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'code/:id',
      name: '签到列表',
      component: resolve => {
        require(['@/views/sign/code'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }
    ]
  },
  {
    path: '/user',
    name: '用户管理',
    component: Main,
    children: [{
      path: 'check',
      name: '审核用户',
      component: resolve => {
        require(['@/views/user/check'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'manage',
      name: '我的创建',
      component: resolve => {
        require(['@/views/user/manage'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }, {
      path: 'query',
      name: '社团用户',
      component: resolve => {
        require(['@/views/user/query'], resolve)
      },
      meta: {
        isLogin: true,
        isAuth: true
      }
    }]
  },


  {
    path: '/',
    name: '',
    component: Main,
    children: [{
      path: '/',
      redirect: {
        path: '/home'
      }
    },
      {
        path: 'home',
        name: '首页',
        component: resolve => {
          require(['@/views/home'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      },
      {
        path: 'userinfo',
        name: '我的信息',
        component: resolve => {
          require(['@/views/myinfo'], resolve)
        },
        meta: {
          isLogin: true,
          isAuth: true
        }
      }
    ]
  },
  {
    path: "/message",
    name: 'message',
    component: resolve => {
      require(['@/views/message'], resolve)
    },
    meta: {
      isLogin: false,
      isAuth: false
    },
  },
  {
    path: "/qrcode/:id",
    name: 'qrcode',
    component: resolve => {
      require(['@/views/qrcode'], resolve)
    },
    meta: {
      isLogin: false,
      isAuth: false
    }
  }]

// 导出
export const routers = [
  loginRouter,
  ...appRouter,
  page404Router
]
