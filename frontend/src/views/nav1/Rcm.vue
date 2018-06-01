<template>
	<section>
		<!--查询-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.name" placeholder="姓名"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getRcms">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--推荐列表-->
		<el-table :data="recommends" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55">
			</el-table-column >
			<el-table-column type="index" label="序号" width="100">
			</el-table-column>
			<el-table-column prop="rcmId" label="推荐ID" width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="rcmSetupdate" label="创建日期" width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="rcmSetuptime" label="创建时间" width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="rcmerId" label="推荐人ID" width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="rcmDate" label="推荐日期" format="yyyy-MM-dd" width="120" sortable>
			</el-table-column>
			<el-table-column prop="rcmRcmername" label="推荐人姓名" min-width="150">
			</el-table-column>
			<el-table-column prop="rcmRcmertype" label="推荐人类型" width="150" :formatter="formatRcmerType" sortable>
			</el-table-column>
			<el-table-column prop="rcmContent" label="推荐内容" width="230" sortable>
			</el-table-column>
			<el-table-column prop="eveResult" label="赛果" min-width="200" sortable>
			</el-table-column>
			<el-table-column prop="rcmResult" label="推单结果" width="120" :formatter="formatRcmResult">
			</el-table-column>
			<el-table-column prop="rcmIntroduction" label="推荐简介" width="150" >
			</el-table-column>
			<el-table-column prop="eveHometeam" label="主队" width="100" sortable>
			</el-table-column>
			<el-table-column prop="eveVisitteam" label="客队" width="100" sortable>
			</el-table-column>
			<el-table-column prop="rcmPayflag" label="付费标志" width="120" :formatter="formatPayFlag" sortable >
			</el-table-column>
			<el-table-column prop="rcmTime" label="推荐时间" min-width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="eveStartdate" label="赛事开始日期" width="150" sortable>
			</el-table-column>
			<el-table-column prop="eveStarttime" label="赛事开始时间" width="120" >
			</el-table-column>
			<el-table-column prop="eveStatus" label="赛事状态" min-width="120" :formatter="formateveStatus" sortable>
			</el-table-column>
			<el-table-column prop="eveLeaguetype" label="联赛种类" width="120" sortable>
			</el-table-column>
			<el-table-column prop="eveBalltype" label="归属球种" width="120" sortable>
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>

		<!--批量删除-->
		<el-col :span="24" class="toolbar">
			<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
			<el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
			</el-pagination>
		</el-col>

		<!--编辑推荐列表-->
		<el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
				<el-form-item label="推荐人" prop="rcmRcmerid">
					<el-select v-model="editForm.rcmRcmerid" placeholder="请选择推荐人">
						<el-option v-for="item in orgnazations" :key="item.orgId" :label="item.orgName" :value="item.orgId+''" ></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="推荐简介" >
					<el-input v-model="editForm.rcmIntroduction" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="付费标志">
					<el-radio-group v-model="editForm.rcmPayflag">
						<el-radio class="radio" label="0">免费</el-radio>
						<el-radio class="radio" label="1">付费</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="推荐时间">
					<el-col :span="11">
						<el-date-picker type="date" placeholder="选择日期" v-model="editForm.rcmDate" @change="setEditRcmDate" format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
					</el-col>
					<el-col class="line" :span="2">-</el-col>
					<el-col :span="11">
						<el-time-picker type="fixed-time" placeholder="选择时间" v-model="editForm.rcmTime"  style="width: 100%;"></el-time-picker>
					</el-col>
				</el-form-item>
				<el-form-item label="推荐内容" >
					<el-input v-model="editForm.rcmContent" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="推单结果">
					<el-radio-group v-model="editForm.rcmResult">
						<el-radio class="radio" label="2">红</el-radio>
						<el-radio class="radio" label="1">走</el-radio>
						<el-radio class="radio" label="0">黑</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="赛事时间">
					<el-col :span="11">
						<el-date-picker type="date" placeholder="选择日期" v-model="editForm.eveStartdate"  style="width: 100%;"></el-date-picker>
					</el-col>
					<el-col class="line" :span="2">-</el-col>
					<el-col :span="11">
						<el-time-picker type="fixed-time" placeholder="选择时间" v-model="editForm.eveStarttime" style="width: 100%;"></el-time-picker>
					</el-col>
				</el-form-item>
				<el-form-item label="赛事状态">
					<el-radio-group v-model="editForm.eveStatus">
						<el-radio class="radio" label="0">未开始</el-radio>
						<el-radio class="radio" label="1">正在进行</el-radio>
						<el-radio class="radio" label="2">已结束</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="联赛种类" >
					<el-input v-model="editForm.eveLeaguetype" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="归属球种" >
					<el-input v-model="editForm.eveBalltype" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="主队" >
					<el-input v-model="editForm.eveHometeam" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="客队" >
					<el-input v-model="editForm.eveVisitteam" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="赛果" >
					<el-input v-model="editForm.eveResult" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="创建日期" v-model="editForm.rcmSetupdate" v-if="false"></el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

		<!--新增推荐-->
		<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
				<el-form-item label="推荐人" prop="rcmerId">
					<el-select v-model="addForm.rcmerId" placeholder="请选择推荐人">
						<el-option v-for="item in orgnazations" :key="item.orgId" :label="item.orgName" :value="item.orgId +''" ></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="推荐简介" >
					<el-input v-model="addForm.rcmIntroduction" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="付费标志">
					<el-radio-group v-model="addForm.rcmPayFlag">
						<el-radio class="radio" label="0">免费</el-radio>
						<el-radio class="radio" label="1">付费</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="推荐时间">
					<el-col :span="11">
						<el-date-picker type="date" placeholder="选择日期" v-model="addForm.rcmDate" @change="setRcmDate" format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
					</el-col>
					<el-col class="line" :span="2">-</el-col>
					<el-col :span="11">
						<el-time-picker type="fixed-time" placeholder="选择时间" v-model="addForm.rcmTime" style="width: 100%;"></el-time-picker>
					</el-col>
				</el-form-item>
				<el-form-item label="推荐内容" >
					<el-input v-model="addForm.rcmContent" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="推单结果">
					<el-radio-group v-model="addForm.rcmResult">
						<el-radio class="radio" label="2">红</el-radio>
						<el-radio class="radio" label="1">走</el-radio>
						<el-radio class="radio" label="0">黑</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="赛事时间">
					<el-col :span="11">
						<el-date-picker params placeholder="选择日期" v-model="addForm.eventStartDate" @change="setEventStartDate" format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
					</el-col>
					<el-col class="line" :span="2">-</el-col>
					<el-col :span="11">
						<el-time-picker type="fixed-time" placeholder="选择时间" v-model="addForm.eventStartTime" style="width: 100%;"></el-time-picker>
					</el-col>
				</el-form-item>
				<el-form-item label="赛事状态">
					<el-radio-group v-model="addForm.eventStatus">
						<el-radio class="radio" label="0">未开始</el-radio>
						<el-radio class="radio" label="1">正在进行</el-radio>
						<el-radio class="radio" label="2">已结束</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="联赛种类" >
					<el-input v-model="addForm.eventLeagueType" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="归属球种" >
					<el-input v-model="addForm.eventBallType" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="主队" >
					<el-input v-model="addForm.eventHomeTeam" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="客队" >
					<el-input v-model="addForm.eventVisitTeam" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="赛果" >
					<el-input v-model="addForm.eventResult" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>
	</section>
</template>

<script>
import util from "../../common/js/util";
import * as RcmMethod from "../../api/api";

export default {
  data() {
    return {
      filters: {
        name: ""
      },
      recommends: [],
      orgnazations: [],
      total: 0,
      page: 1,
      listLoading: false,
      sels: [], //列表选中列

      editFormVisible: false, //编辑界面是否显示
      editLoading: false,
      editFormRules: {
        rcmer_id: [{ required: true, message: "请输入姓名", trigger: "blur" }]
      },
      //编辑界面数据
      editForm: {
        rcmId: "",
        rcmSetupDate: "",
        rcmSetupTime: "",
        rcmerId: "",
        rcmerName: "",
        rcmerType: "",
        rcmIntroduction: "",
        rcmPayFlag: "",
        rcmDate: "",
        rcmTime: "",
        rcmContent: "",
        rcmResult: "",
        eventStartdate: "",
        eventStarttime: "",
        eventStatus: "",
        eventLeagueType: "",
        eventBallType: "",
        eventHomeTeam: "",
        eventVisitTeam: "",
        eventResult: ""
      },

      addFormVisible: false, //新增界面是否显示
      addLoading: false,
      addFormRules: {
        rcmerId: [
          { required: true, message: "请选择媒体机构", trigger: "blur" }
        ]
      },
      //新增界面数据
      addForm: {
        rcmId: "",
        rcmSetupDate: "",
        rcmSetupTime: "",
        rcmerId: "",
        rcmerName: "",
        rcmerType: "",
        rcmIntroduction: "",
        rcmPayFlag: "",
        rcmDate: "",
        rcmTime: "",
        rcmContent: "",
        rcmResult: "",
        eventStartDate: "",
        eventStartTime: "",
        eventStatus: "",
        eventLeagueType: "",
        eventBallType: "",
        eventHomeTeam: "",
        eventVisitTeam: "",
        eventResult: ""
      }
    };
  },
  mounted() {
    this.getRcms();
    RcmMethod.getOrgList().then(res => {
      this.total = res.data.result.length;
      this.orgnazations = res.data.result;
    });
  },
  methods: {
    //获取推单列表--调用接口--getUserListPage
    getRcms() {
      let para = {
        page: this.page,
        name: this.filters.name
      };
      this.listLoading = true;
      RcmMethod.getRcmList(para).then(res => {
        this.total = res.data.result.length;
        this.recommends = res.data.result;
        this.listLoading = false;
      });
    },
    //新增推单--调用接口--addUser
    addSubmit: function() {
      this.$refs["addForm"].validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            this.addLoading = true;
            let para = Object.assign({}, this.addForm);
            RcmMethod.addRcm(para).then(res => {
              this.addLoading = false;
              let code = res.data.status;
              let message = res.data.message;
              if (code != 200) {
                this.$message({
                  message: message,
                  type: "error"
                });
              } else {
                this.$message({
                  message: "提交成功",
                  type: "success"
                });
              }
              this.$refs["addForm"].resetFields();
              this.addFormVisible = false;
              this.getRcms();
            });
          });
        }
      });
    },
    //编辑推单--调用接口--editUser
    editSubmit: function() {
      this.$refs["editForm"].validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            this.editLoading = true;
            let para = Object.assign({}, this.editForm);
            RcmMethod.editRcm(para).then(res => {
              this.editLoading = false;
              let code = res.data.status;
              let message = res.data.message;
              if (code != 200) {
                this.$message({
                  message: message,
                  type: "error"
                });
              } else {
                this.$message({
                  message: "提交成功",
                  type: "success"
                });
              }
              this.$refs["editForm"].resetFields();
              this.editFormVisible = false;
              this.getRcms();
            });
          });
        }
      });
    },
    //删除选中推单--调用接口--removeUser
    handleDel: function(index, row) {
      this.$confirm("确认删除该记录吗?", "提示", {
        type: "warning"
      })
        .then(() => {
          this.listLoading = true;
          let para = row.rcmId;
          RcmMethod.removeRcm(para).then(res => {
            this.listLoading = false;
            let code = res.data.status;
            let message = res.data.message;
            if (code != 200) {
              this.$message({
                message: message,
                type: "error"
              });
            } else {
              this.$message({
                message: "删除成功",
                type: "success"
              });
            }
            this.getRcms();
          });
        })
        .catch(() => {});
    },
    //批量删除推单--调用接口--batchRemoveUser
    batchRemove: function() {
      var ids = this.sels.map(item => item.rcmId).toString();
      this.$confirm("确认删除选中记录吗？", "提示", {
        type: "warning"
      })
        .then(() => {
          this.listLoading = true;
          let para = { ids: ids };
          RcmMethod.batchRemoveRcm(para).then(res => {
            this.listLoading = false;
            let code = res.data.status;
            let message = res.data.message;
            if (code != 200) {
              this.$message({
                message: message,
                type: "error"
              });
            } else {
              this.$message({
                message: "删除成功",
                type: "success"
              });
            }
            this.getRcms();
          });
        })
        .catch(() => {});
    },
    //推单类型翻译 免费-付费（）
    formatPayFlag: function(row, column) {
      return row.rcmPayflag == 1
        ? "付费"
        : row.rcmPayflag == 0 ? "免费" : "未知";
    },
    //推荐人类型翻译 名人-公众号-其他
    formatRcmerType: function(row, column) {
      return row.rcmRcmertype == 1
        ? "公众号"
        : row.rcmRcmertype == 2
          ? "名人"
          : row.rcmRcmertype == 3 ? "内部群" : "其他";
    },
    //推单结果翻译 黑-走水-红
    formatRcmResult: function(row, column) {
      return row.rcmResult == 0
        ? "黑"
        : row.rcmResult == 1 ? "走水" : row.rcmResult == 2 ? "红" : "其他";
    },
    //赛事状态翻译 未开始-正在进行-结束
    formateveStatus: function(row, column) {
      return row.eveStatus == 0
        ? "未开始"
        : row.eveStatus == 1
          ? "正在进行"
          : row.eveStatus == 2 ? "结束" : "其他";
    },

    //日期时间化
    setRcmDate: function(val) {
      this.addForm.rcmDate = val;
    },
    setEventStartDate: function(val) {
      this.addForm.eventStartDate = val;
    },
    setEditRcmDate: function(val) {
      this.editForm.rcmDate = val;
    },
    setEditEventStartDate: function(val) {
      this.editForm.eveStartdate = val;
    },

    //显示编辑界面
    handleEdit: function(index, row) {
      this.editFormVisible = true;
      this.editForm = Object.assign({}, row);
    },
    //显示新增界面
    handleAdd: function() {
      this.addFormVisible = true;
      this.addForm = {};
    },
    selsChange: function(sels) {
      this.sels = sels;
    },
    handleCurrentChange(val) {
      this.page = val;
      this.getRcms();
    }
  }
};
</script>

<style scoped>
</style>