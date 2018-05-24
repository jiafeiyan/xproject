import axios from 'axios';
import MockAdapter from 'axios-mock-adapter';
import {
  LoginUsers,
  Users,
  Recommend,
  Orgnization,
  Board
} from './data/user';
let _Users = Users;
let _Recommend = Recommend;
let _Orgnization = Orgnization;
let _Board = Board;

export default {
  /**
   * mock bootstrap
   */
  bootstrap() {
    let mock = new MockAdapter(axios);

    // mock success request
    mock.onGet('/success').reply(200, {
      msg: 'success'
    });

    // mock error request
    mock.onGet('/error').reply(500, {
      msg: 'failure'
    });

    //登录
    mock.onPost('/login').reply(config => {
      let {
        username,
        password
      } = JSON.parse(config.data);
      return new Promise((resolve, reject) => {
        let user = null;
        setTimeout(() => {
          let hasUser = LoginUsers.some(u => {
            if (u.username === username && u.password === password) {
              user = JSON.parse(JSON.stringify(u));
              user.password = undefined;
              return true;
            }
          });

          if (hasUser) {
            resolve([200, {
              code: 200,
              msg: '请求成功',
              user
            }]);
          } else {
            resolve([200, {
              code: 500,
              msg: '账号或密码错误'
            }]);
          }
        }, 1000);
      });
    });

    /**
     * ===================================================推荐相关开始============================================================
     */

    /**
     * 新增赛事推荐
     */
    mock.onGet('/rcm/add').reply(config => {
      let {
        rcmerId,
        rcmerName,
        rcmerType,
        rcmIntrocution,
        rcmPayFlag,
        rcmDate,
        rcmTime,
        rcmContent,
        rcmResult,
        eventStartDate,
        eventStartTime,
        eventStatus,
        eventLeagueType,
        eventBallType,
        eventHomeTeam,
        eventVisitTeam,
        eventResult,
      } = config.params;
      _Recommend.push({
        rcmerId: rcmerId,
        rcmerName: rcmerName,
        rcmerType: rcmerType,
        rcmIntrocution: rcmIntrocution,
        rcmPayFlag: rcmPayFlag,
        rcmDate: rcmDate,
        rcmTime: rcmTime,
        rcmContent: rcmContent,
        rcmResult: rcmResult,
        eventStartDate: eventStartDate,
        eventStartTime: eventStartTime,
        eventStatus: eventStatus,
        eventLeagueType: eventLeagueType,
        eventBallType: eventBallType,
        eventHomeTeam: eventHomeTeam,
        eventVisitTeam: eventVisitTeam,
        eventResult: eventResult,
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '新增成功'
          }]);
        }, 500);
      });
    });

    /**
     * 推荐删除
     */
    mock.onGet('/rcm/remove').reply(config => {
      let {
        id
      } = config.params;
      _Recommend = _Recommend.filter(u => u.id !== id);
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '删除成功'
          }]);
        }, 500);
      });
    });

    /**
     * 推荐批量删除
     */
    mock.onGet('/rcm/batchremove').reply(config => {
      let {
        ids
      } = config.params;
      ids = ids.split(',');
      _Recommend = _Recommend.filter(u => !ids.includes(u.id));
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '删除成功'
          }]);
        }, 500);
      });
    });

    /**
     * 修改推荐内容
     */
    mock.onGet('/rcm/edit').reply(config => {
      let {
        rcmerId,
        rcmerName,
        rcmerType,
        rcmIntrocution,
        rcmPayFlag,
        rcmDate,
        rcmTime,
        rcmContent,
        rcmResult,
        eventStartDate,
        eventStartTime,
        eventStatus,
        eventLeagueType,
        eventBallType,
        eventHomeTeam,
        eventVisitTeam,
        eventResult,
      } = config.params;
      _Recommend.some(u => {
        if (u.id === id) {
          u.rcmerId = rcmerId;
          u.rcmerName = rcmerName;
          u.rcmerType = rcmerType;
          u.rcmIntrocution = rcmIntrocution;
          u.rcmPayFlag = rcmPayFlag;
          u.rcmDate = rcmDate;
          u.rcmTime = rcmTime;
          u.rcmContent = rcmContent;
          u.rcmResult = rcmResult;
          u.eventStartDate = eventStartDate;
          u.eventStartTime = eventStartTime;
          u.eventStatus = eventStatus;
          u.eventLeagueType = eventLeagueType;
          u.eventBallType = eventBallType;
          u.eventHomeTeam = eventHomeTeam;
          u.eventVisitTeam = eventVisitTeam;
          u.eventResult = eventResult;
          return true;
        }
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '编辑成功'
          }]);
        }, 500);
      });
    });


    /**
     * 查询所有推荐赛事
     */
    mock.onGet('/rcm/list').reply(config => {
      let {
        name
      } = config.params;
      let mockRecommends = _Recommend.filter(user => {
        if (name && user.name.indexOf(name) == -1) return false;
        return true;
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            recommends: mockRecommends
          }]);
        }, 1000);
      });
    });


    /**
     * 分页查询推荐赛事
     * 获取推荐赛事列表
     * req为前端获取的请求对象
     * _Recommend为MOCK服务器响应对象
     * args为mock/data/user.js单条Recommend记录
     * page,为前端上送的分页参数
     * name,为前端上送的模糊查询关键字
     */
    mock.onGet('/rcm/listpage').reply(req => {
      let {
        page,
        name
      } = req.params;
      //遍历_Recommend对象,返回符合条件的对象,生成新的数组
      let mockRecommends = _Recommend.filter(args => {
        if (name && args.name.indexOf(name) == -1) return false;
        return true;
      });
      let total = mockRecommends.length;
      mockRecommends = mockRecommends.filter((u, index) => index < 20 * page && index >= 20 * (page - 1));
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            total: total,
            recommends: mockRecommends
          }]);
        }, 1000);
      });
    });

    /**
     * ===================================================推荐相关结束============================================================
     */



    /**
     * ===================================================媒体机构相关开始============================================================
     */

    /**
     * 新增媒体机构
     */
    mock.onGet('/org/add').reply(config => {
      let {
        orgName,
        orgPic,
        orgQRPic,
        orgIntroduction,
        orgMotto,
        orgType,
        orgRecommandIndex,
        orgPriority,
        orgKeyword,
        orgBelong,
        orgContacts,
      } = config.params;
      _Orgnization.push({
        orgName: orgName,
        orgPic: orgPic,
        orgQRPic: orgQRPic,
        orgIntroduction: orgIntroduction,
        orgMotto: orgMotto,
        orgType: orgType,
        orgRecommandIndex: orgRecommandIndex,
        orgPriority: orgPriority,
        orgKeyword: orgKeyword,
        orgBelong: orgBelong,
        orgContacts: orgContacts,
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '新增成功'
          }]);
        }, 500);
      });
    });

    /**
     * 媒体机构删除
     */
    mock.onGet('/org/remove').reply(config => {
      let {
        id
      } = config.params;
      _Orgnization = _Orgnization.filter(u => u.id !== id);
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '删除成功'
          }]);
        }, 500);
      });
    });

    /**
     * 媒体机构批量删除
     */
    mock.onGet('/org/batchremove').reply(config => {
      let {
        ids
      } = config.params;
      ids = ids.split(',');
      _Orgnization = _Orgnization.filter(u => !ids.includes(u.id));
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '删除成功'
          }]);
        }, 500);
      });
    });

    /**
     * 修改媒体机构信息
     */
    mock.onGet('/org/edit').reply(config => {
      let {
        orgName,
        orgPic,
        orgQRPic,
        orgIntroduction,
        orgMotto,
        orgType,
        orgRecommandIndex,
        orgPriority,
        orgKeyword,
        orgBelong,
        orgContacts,
      } = config.params;
      _Orgnization.some(u => {
        if (u.id === id) {
          u.orgName = orgName;
          u.orgPic = orgPic;
          u.orgQRPic = orgQRPic;
          u.orgIntroduction = orgIntroduction;
          u.orgMotto = orgMotto;
          u.orgType = orgType;
          u.orgRecommandIndex = orgRecommandIndex;
          u.orgPriority = orgPriority;
          u.orgKeyword = orgKeyword;
          u.orgBelong = orgBelong;
          u.orgContacts = orgContacts;
          return true;
        }
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '编辑成功'
          }]);
        }, 500);
      });
    });


    /**
     * 查询所有媒体机构
     */
    mock.onGet('/org/list').reply(config => {
      let {
        name
      } = config.params;
      let mockOrgnizations = _Orgnization.filter(user => {
        if (name && user.name.indexOf(name) == -1) return false;
        return true;
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            orgnizations: mockOrgnizations
          }]);
        }, 1000);
      });
    });


    /**
     * 分页查询推荐赛事
     * 获取推荐赛事列表
     * req为前端获取的请求对象
     * _Recommend为MOCK服务器响应对象
     * args为mock/data/user.js单条Recommend记录
     * page,为前端上送的分页参数
     * name,为前端上送的模糊查询关键字
     */
    mock.onGet('/org/listpage').reply(req => {
      let {
        page,
        name
      } = req.params;
      //遍历_Recommend对象,返回符合条件的对象,生成新的数组
      let mockOrgnizations = _Orgnization.filter(args => {
        if (name && args.name.indexOf(name) == -1) return false;
        return true;
      });
      let total = mockOrgnizations.length;
      mockOrgnizations = mockOrgnizations.filter((u, index) => index < 20 * page && index >= 20 * (page - 1));
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            total: total,
            orgnizations: mockOrgnizations
          }]);
        }, 1000);
      });
    });

    /**
     * ===================================================机构媒体相关结束============================================================
     */


    /**
     * ===================================================公告相关开始============================================================
     */

    /**
     * 新增公告
     */
    mock.onGet('/brd/add').reply(config => {
      let {
        rcmDate,
        rcmTime,
        rcmContent,
        rcmReason,
      } = config.params;
      _Board.push({
        rcmDate: rcmDate,
        rcmTime: rcmTime,
        rcmContent: rcmContent,
        rcmReason: rcmReasonl
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '新增成功'
          }]);
        }, 500);
      });
    });

    /**
     * 删除公告
     */
    mock.onGet('/brd/remove').reply(config => {
      let {
        id
      } = config.params;
      _Board = _Board.filter(u => u.id !== id);
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '删除成功'
          }]);
        }, 500);
      });
    });

    /**
     * 公告批量删除
     */
    mock.onGet('/brd/batchremove').reply(config => {
      let {
        ids
      } = config.params;
      ids = ids.split(',');
      _Board = _Board.filter(u => !ids.includes(u.id));
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '删除成功'
          }]);
        }, 500);
      });
    });

    /**
     * 修改公告内容
     */
    mock.onGet('/brd/edit').reply(config => {
      let {
        rcmDate,
        rcmTime,
        rcmContent,
        rcmReason,
      } = config.params;
      _Board.some(u => {
        if (u.id === id) {
          u.rcmDate = rcmDate;
          u.rcmTime = rcmTime;
          u.rcmContent = rcmContent;
          u.rcmReason = rcmReasonl
          return true;
        }
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            code: 200,
            msg: '编辑成功'
          }]);
        }, 500);
      });
    });


    /**
     * 查询所有公告
     */
    mock.onGet('/brd/list').reply(config => {
      let {
        name
      } = config.params;
      let mockBoards = _Board.filter(user => {
        if (name && user.name.indexOf(name) == -1) return false;
        return true;
      });
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            boards: mockBoards
          }]);
        }, 1000);
      });
    });


    /**
     * 分页查询公告
     * 获取推荐赛事列表
     * req为前端获取的请求对象
     * _Recommend为MOCK服务器响应对象
     * args为mock/data/user.js单条Recommend记录
     * page,为前端上送的分页参数
     * name,为前端上送的模糊查询关键字
     */
    mock.onGet('/brd/listpage').reply(req => {
      let {
        page,
        name
      } = req.params;
      //遍历_Recommend对象,返回符合条件的对象,生成新的数组
      let mockBoards = _Board.filter(args => {
        if (name && args.name.indexOf(name) == -1) return false;
        return true;
      });
      let total = mockBoards.length;
      mockBoards = mockBoards.filter((u, index) => index < 20 * page && index >= 20 * (page - 1));
      return new Promise((resolve, reject) => {
        setTimeout(() => {
          resolve([200, {
            total: total,
            boards: mockBoards
          }]);
        }, 1000);
      });
    });

    /**
     * ===================================================推荐相关结束============================================================
     */


  }
};