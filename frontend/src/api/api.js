import axios from 'axios';

let base = 'http://127.0.0.1:5000';

// let instance = axios.create({
//     headers: {'content-type': 'application/x-www-form-urlencoded'},
//     withCredentials: true
 
//   });

/**
 * 登录相关api
 * requestLogin--登录请求
 * 
 */
export const requestLogin = params => { return axios.post(`/crossApi/manager/login`, params).then(res => res.data); };


/** 
 * 推单相关api
 * addRcm--增加
 * removeRcm--删除
 * batchRemoveRcm--批量删除
 * editRcm--更新修改
 * getRcmList--查询所有推单
 * getRcmListPage--分页查询推单
 * 
*/
export const addRcm = params => { return axios.put(`/crossApi/manager/rcm/add`, { params: params }); };

export const removeRcm = params => { return axios.delete(`/crossApi/manager/rcm/remove/${params}`)};

export const batchRemoveRcm = params => { return axios.delete(`/crossApi/manager/rcm/batchremove/`,{ params: params})};

export const editRcm = params => { return axios.post(`/crossApi/manager/rcm/edit`, { params: params }); };

export const getRcmList = params => { return axios.get(`/crossApi/manager/rcm/list`, { params: params }); };

export const getRcmListPage = params => { return axios.get(`/crossApi/manager/rcm/listpage`, { params: params }); };


/** 
 * 媒体机构相关api
 * addOrg--增加
 * removeOrg--删除
 * batchRemoveOrg--批量删除
 * editOrg--更新修改
 * getOrgList--查询所有推单
 * getOrgListPage--分页查询推单
 * 
*/
export const addOrg = params => { return axios.put(`/crossApi/manager/org/add`, { params: params }); };

export const removeOrg = params => { return axios.delete(`/crossApi/manager/org/remove/${params}`); };

export const batchRemoveOrg = params => { return axios.delete(`/crossApi/manager/org/batchremove`, { params: params }); };

export const editOrg = params => { return axios.post(`/crossApi/manager/org/edit`, { params: params }); };

export const getOrgList = params => { return axios.get(`/crossApi/manager/org/list`, { params: params }); };

export const getOrgListPage = params => { return axios.get(`/crossApi/manager/org/listpage`, { params: params }); };


/** 
 * 公告相关api
 * addBrd--增加
 * removeBrd--删除
 * batchRemoveBrd--批量删除
 * editBrd--更新修改
 * getBrdList--查询所有推单
 * getBrdListPage--分页查询推单
 * 
*/
export const addBrd = params => { return axios.put(`/crossApi/manager/brd/add`, { params: params }); };

export const removeBrd = params => { return axios.delete(`/crossApi/manager/brd/remove/${params}`); };

export const batchRemoveBrd = params => { return axios.delete(`/crossApi/manager/brd/batchremove`, { params: params }); };

export const editBrd = params => { return axios.post(`/crossApi/manager/brd/edit`, { params: params }); };

export const getBrdList = params => { return axios.get(`/crossApi/manager/brd/list`, { params: params }); };

export const getBrdListPage = params => { return axios.get(`/crossApi/manager/brd/listpage`, { params: params }); };
