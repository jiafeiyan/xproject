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
			<el-table-column prop="rcm_id" label="推荐ID" width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="rcm_setupdate" label="创建日期" width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="rcm_setuptime" label="创建时间" width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="rcmer_id" label="推荐人ID" width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="rcm_date" label="推荐日期" format="yyyy-MM-dd" width="120" sortable>
			</el-table-column>
			<el-table-column prop="rcm_rcmername" label="推荐人姓名" min-width="150">
			</el-table-column>
			<el-table-column prop="rcm_rcmertype" label="推荐人类型" width="150" :formatter="formatRcmerType" sortable>
			</el-table-column>
			<el-table-column prop="rcm_content" label="推荐内容" width="230" sortable>
			</el-table-column>
			<el-table-column prop="eve_result" label="赛果" min-width="200" sortable>
			</el-table-column>
			<el-table-column prop="rcm_result" label="推单结果" width="120" :formatter="formatRcmResult">
			</el-table-column>
			<el-table-column prop="rcm_introduction" label="推荐简介" width="150" >
			</el-table-column>
			<el-table-column prop="eve_hometeam" label="主队" width="100" sortable>
			</el-table-column>
			<el-table-column prop="eve_visitteam" label="客队" width="100" sortable>
			</el-table-column>
			<el-table-column prop="rcm_payflag" label="付费标志" width="120" :formatter="formatPayFlag" sortable >
			</el-table-column>
			<el-table-column prop="rcm_time" label="推荐时间" min-width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="eve_startdate" label="赛事开始日期" width="150" sortable>
			</el-table-column>
			<el-table-column prop="eve_starttime" label="赛事开始时间" width="120" >
			</el-table-column>
			<el-table-column prop="eve_status" label="赛事状态" min-width="120" :formatter="formateveStatus" sortable>
			</el-table-column>
			<el-table-column prop="eve_leaguetype" label="联赛种类" width="120" sortable>
			</el-table-column>
			<el-table-column prop="eve_balltype" label="归属球种" width="120" sortable>
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
				<el-form-item label="推荐人">
					<el-select v-model="editForm.rcmer_id" placeholder="请选择推荐人">
						<el-option v-for="item in orgnazations" :key="item.org_id" :label="item.org_name" :value="item.org_id" ></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="推荐简介" >
					<el-input v-model="editForm.rcm_introduction" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="付费标志">
					<el-radio-group v-model="editForm.rcm_payflag">
						<el-radio class="radio" :label="0">免费</el-radio>
						<el-radio class="radio" :label="1">付费</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="推荐时间">
					<el-col :span="11">
						<el-date-picker type="date" placeholder="选择日期" v-model="editForm.rcm_date" @change="setEditRcmDate" format="yyyy-MM-dd" style="width: 100%;"></el-date-picker>
					</el-col>
					<el-col class="line" :span="2">-</el-col>
					<el-col :span="11">
						<el-time-picker type="fixed-time" placeholder="选择时间" v-model="editForm.rcm_time" @change="setEditEventStartDate" format="yyyy-MM-dd"  style="width: 100%;"></el-time-picker>
					</el-col>
				</el-form-item>
				<el-form-item label="推荐内容" >
					<el-input v-model="editForm.rcm_content" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="推单结果">
					<el-radio-group v-model="editForm.rcm_result">
						<el-radio class="radio" :label="2">红</el-radio>
						<el-radio class="radio" :label="1">走</el-radio>
						<el-radio class="radio" :label="0">黑</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="赛事时间">
					<el-col :span="11">
						<el-date-picker type="date" placeholder="选择日期" v-model="editForm.eve_startdate"  style="width: 100%;"></el-date-picker>
					</el-col>
					<el-col class="line" :span="2">-</el-col>
					<el-col :span="11">
						<el-time-picker type="fixed-time" placeholder="选择时间" v-model="editForm.eve_starttime" style="width: 100%;"></el-time-picker>
					</el-col>
				</el-form-item>
				<el-form-item label="赛事状态">
					<el-radio-group v-model="editForm.eve_status">
						<el-radio class="radio" :label="0">未开始</el-radio>
						<el-radio class="radio" :label="1">正在进行</el-radio>
						<el-radio class="radio" :label="2">已结束</el-radio>
					</el-radio-group>
				</el-form-item>
				<el-form-item label="联赛种类" >
					<el-input v-model="editForm.eve_leaguetype" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="归属球种" >
					<el-input v-model="editForm.eve_balltype" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="主队" >
					<el-input v-model="editForm.eve_hometeam" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="客队" >
					<el-input v-model="editForm.eve_visitteam" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="赛果" >
					<el-input v-model="editForm.eve_result" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="创建日期" v-model="editForm.rcm_setupdate" v-if="false"></el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

		<!--新增推荐-->
		<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
				<el-form-item label="推荐人">
					<el-select v-model="addForm.rcmerId" placeholder="请选择推荐人">
						<el-option v-for="item in orgnazations" :key="item.org_id" :label="item.org_name" :value="item.org_id" ></el-option>
					</el-select>
				</el-form-item>
				<el-form-item label="推荐简介" >
					<el-input v-model="addForm.rcmIntrocution" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="付费标志">
					<el-radio-group v-model="addForm.rcmPayFlag">
						<el-radio class="radio" :label="0">免费</el-radio>
						<el-radio class="radio" :label="1">付费</el-radio>
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
						<el-radio class="radio" :label="2">红</el-radio>
						<el-radio class="radio" :label="1">走</el-radio>
						<el-radio class="radio" :label="0">黑</el-radio>
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
						<el-radio class="radio" :label="0">未开始</el-radio>
						<el-radio class="radio" :label="1">正在进行</el-radio>
						<el-radio class="radio" :label="2">已结束</el-radio>
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
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }]
      },
      //编辑界面数据
      editForm: {
        rcm_id: "",
        rcm_setupdate: "",
        rcm_setuptime: "",
        rcmer_id: "",
        rcmer_name: "",
        rcmer_type: "",
        rcm_introduction: "",
        rcm_payflag: "",
        rcm_date: "",
        rcm_time: "",
        rcm_content: "",
        rcm_result: "",
        eve_startdate: "",
        eve_starttime: "",
        eve_status: "",
        eve_leaguetype: "",
        eve_balltype: "",
        eve_hometeam: "",
        eve_visitteam: "",
        eve_result: ""
      },

      addFormVisible: false, //新增界面是否显示
      addLoading: false,
      addFormRules: {
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }]
      },
      //新增界面数据
      addForm: {
        rcmId: "",
        rcmSetupDate: "",
        rcmSetupTime: "",
        rcmerId: "",
        rcmerName: "",
        rcmerType: "",
        rcmIntrodution: "",
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
      this.$refs.addForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            this.addLoading = true;
            let para = Object.assign({}, this.addForm);
            RcmMethod.addRcm(para).then(res => {
              this.addLoading = false;
              this.$message({
                message: "提交成功",
                type: "success"
              });
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
      this.$refs.editForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            this.editLoading = true;
            let para = Object.assign({}, this.editForm);
            RcmMethod.editRcm(para).then(res => {
              this.editLoading = false;
              this.$message({
                message: "提交成功",
                type: "success"
              });
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
          let para = row.rcm_id;
          RcmMethod.removeRcm(para).then(res => {
            this.listLoading = false;
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getRcms();
          });
        })
        .catch(() => {});
    },
    //批量删除推单--调用接口--batchRemoveUser
    batchRemove: function() {
      var ids = this.sels.map(item => item.rcm_id).toString();
      this.$confirm("确认删除选中记录吗？", "提示", {
        type: "warning"
      })
        .then(() => {
          this.listLoading = true;
          let para = { ids: ids };
          RcmMethod.batchRemoveRcm(para).then(res => {
            this.listLoading = false;
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getRcms();
          });
        })
        .catch(() => {});
    },
    //推单类型翻译 免费-付费（）
    formatPayFlag: function(row, column) {
      return row.rcm_payflag == 1
        ? "付费"
        : row.rcm_payflag == 0 ? "免费" : "未知";
    },
    //推荐人类型翻译 名人-公众号-其他
    formatRcmerType: function(row, column) {
      return row.rcm_rcmertype == 1
        ? "公众号"
        : row.rcm_rcmertype == 2
          ? "名人"
          : row.rcm_rcmertype == 3 ? "内部群" : "其他";
    },
    //推单结果翻译 黑-走水-红
    formatRcmResult: function(row, column) {
      return row.rcm_result == 0
        ? "黑"
        : row.rcm_result == 1 ? "走水" : row.rcm_result == 2 ? "红" : "其他";
    },
    //赛事状态翻译 未开始-正在进行-结束
    formateveStatus: function(row, column) {
      return row.eve_status == 0
        ? "未开始"
        : row.eve_status == 1
          ? "正在进行"
          : row.eve_status == 2 ? "结束" : "其他";
    },

    //日期时间化
    setRcmDate: function(val) {
      this.addForm.rcmDate = val;
    },
    setEventStartDate: function(val) {
      this.addForm.eventStartDate = val;
    },
    setEditRcmDate: function(val) {
      this.editForm.rcm_date = val;
    },
    setEditEventStartDate: function(val) {
      this.editForm.eve_startdate = val;
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