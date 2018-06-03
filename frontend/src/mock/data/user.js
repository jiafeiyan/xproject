import Mock from 'mockjs';
const LoginUsers = [{
  id: 1,
  username: 'admin',
  password: '123456',
  avatar: '../static/localPic/user.png',
  name: ''
}];

const Users = [];
const Orgnization = [];
const Recommend = [];
const Board = [];

for (let i = 0; i < 86; i++) {
  Users.push(Mock.mock({
    id: Mock.Random.guid(),
    name: Mock.Random.cname(),
    addr: Mock.mock('@county(true)'),
    'age|18-60': 1,
    birth: Mock.Random.date(),
    sex: Mock.Random.integer(0, 1)
  }));
}


for (let i = 0; i < 100; i++) {
  Orgnization.push(Mock.mock({
    orgId:Mock.mock('@guid'),
    orgSetupDate:Mock.mock('@date'),
    orgSetupTime:Mock.mock('@time'),
    orgName:Mock.mock('@pick(["红军篮彩", "红军足彩", "红军电竞", "杨毅", "Joker"])'),
    orgPic:Mock.mock('@pick(["暂无", "暂无"])'),
    orgQRPic:Mock.mock('@pick(["暂无", "暂无"])'),
    orgIntroduction:Mock.mock('@pick(["中央电视台著名解说", "知名博主"])'),
    orgMotto:Mock.mock('@pick(["我的三分剑，是地狱的火焰", "红军反买，别墅靠海"])'),
    orgType:Mock.mock('@pick(["1", "2", "3"])'),
    orgRecommandIndex:Mock.mock('@pick(["1", "2", "3", "4", "5"])'),
    orgPriority:Mock.mock('@pick(["1", "2", "3", "4", "5"])'),
    orgKeyword:Mock.mock('@pick(["足球", "篮球", "电竞","o", "u"])'),
    orgBelong:Mock.mock('@pick(["红军工作室", "蓝军工作室"])'),
    orgContacts:Mock.mock('@pick(["18510097098", "18845879901"])'),
  }));
}

for (let i = 0; i < 100; i++) {
  Recommend.push(Mock.mock({
    rcmId:Mock.mock('@guid'),
    rcmSetupDate:Mock.mock('@date'),
    rcmSetupTime:Mock.mock('@time'),
    rcmerId:Mock.mock('@guid'),
    rcmerName:Mock.mock('@pick(["红军篮彩", "红军足彩", "红军电竞", "杨毅", "Joker"])'),
    rcmerType:Mock.mock('@pick(["1", "2", "3"])'),
    rcmIntrocution:Mock.mock('@pick(["勇士决战天王山", "火箭决战天王山"])'),
    rcmPayFlag:Mock.mock('@pick(["0", "1"])'),
    rcmDate:Mock.mock('@date'),
    rcmTime:Mock.mock('@time'),
    rcmContent:Mock.mock('@pick(["勇士VS火箭223大", "骑士VS勇士230小"])'),
    rcmResult:Mock.mock('@pick(["1", "2", "3"])'),
    eventStartDate:Mock.mock('@date'),
    eventStartTime:Mock.mock('@time'),
    eventStatus:Mock.mock('@pick(["1", "2", "3"])'),
    eventLeagueType:Mock.mock('@pick(["世界杯", "奥运会"])'),
    eventBallType:Mock.mock('@pick(["篮球", "足球"])'),
    eventHomeTeam:Mock.mock('@pick(["勇士", "骑士","火箭"])'),
    eventVisitTeam:Mock.mock('@pick(["勇士", "骑士","火箭"])'),
    eventResult:Mock.mock('@pick(["勇士123:骑士130", "勇士133:骑士110", "勇士128:骑士98"])'),
  }));
}

for (let i = 0; i < 100; i++) {
  Board.push(Mock.mock({
    brdId:Mock.mock('@guid'),
    brdSetupDate:Mock.mock('@date'),
    brdSetupTime:Mock.mock('@time'),
    rcmDate:Mock.mock('@date'),
    rcmTime:Mock.mock('@time'),
    rcmContent:Mock.mock('@pick(["勇士决战天王山", "火箭决战天王山"])'),
    rcmReason:Mock.mock('@pick(["勇士决战天王山", "火箭决战天王山"])'),
  }));
}

export {
  LoginUsers,
  Users,
  Recommend,
  Orgnization,
  Board
};