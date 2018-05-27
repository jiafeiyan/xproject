<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.name" placeholder="姓名"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getOrgs">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="orgnizations" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55">
			</el-table-column >
			<el-table-column type="index" label="序号" width="100">
			</el-table-column>
			<el-table-column prop="org_id" label="组织ID" width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="org_setupdate" label="创建日期" width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="org_setuptime" label="创建时间" width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="org_name" label="组织名字" width="120" >
			</el-table-column>
			<el-table-column prop="org_type" label="组织类型" min-width="120" sortable>
			</el-table-column>
			<el-table-column prop="org_recommendindex" label="推荐指数" width="120" sortable>
			</el-table-column>
			<el-table-column prop="org_priority" label="优先级别" width="150" sortable>
			</el-table-column>
			<el-table-column prop="org_keyword" label="专家关键字" width="150" sortable>
			</el-table-column>
			<el-table-column prop="org_pic" label="组织头像" width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="org_qrpic" label="组织二维码" min-width="150" v-if="false">
			</el-table-column>
			<el-table-column prop="org_introduction" label="身份简介" width="250" sortable>
			</el-table-column>
			<el-table-column prop="org_motto" label="宣传语" width="250" sortable>
			</el-table-column>	
			<el-table-column prop="org_belong" label="公众号归属人" width="180" sortable>
			</el-table-column>
			<el-table-column prop="org_contacts" label="归属人联系方式" width="180" sortable >
			</el-table-column>
			<el-table-column label="操作" width="150">
				<template scope="scope">
					<el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
					<el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>

		<!--工具条-->
		<el-col :span="24" class="toolbar">
			<el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
			<el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total" style="float:right;">
			</el-pagination>
		</el-col>

		<!--编辑界面-->
		<el-dialog title="编辑" v-model="editFormVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <el-form-item label="组织名字" >
					<el-input v-model="editForm.org_name" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="身份简介" >
					<el-input v-model="editForm.org_introduction" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="宣传语" >
					<el-input v-model="editForm.org_motto" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="组织类型">
          <el-select v-model="editForm.org_type" placeholder="请选择组织类型">
						<el-option label="公众号" value="1"></el-option>
						<el-option label="名人" value="2"></el-option>
            <el-option label="微博" value="3"></el-option>
            <el-option label="微信" value="4"></el-option>
            <el-option label="QQ" value="5"></el-option>
            <el-option label="其他" value="6"></el-option>
					</el-select>
        </el-form-item>
        <el-form-item label="推荐指数">
					<el-radio-group v-model="editForm.org_recommandindex">
						<el-radio class="radio" :label="1">一</el-radio>
						<el-radio class="radio" :label="2">二</el-radio>
						<el-radio class="radio" :label="3">三</el-radio>
            <el-radio class="radio" :label="4">四</el-radio>
						<el-radio class="radio" :label="5">五</el-radio>
            <el-radio class="radio" :label="6">六</el-radio>
            <el-radio class="radio" :label="7">七</el-radio>
						<el-radio class="radio" :label="8">八</el-radio>
            <el-radio class="radio" :label="9">九</el-radio>
						<el-radio class="radio" :label="10">十</el-radio>
					</el-radio-group>
				</el-form-item>
        <el-form-item label="优先级别">
					<el-radio-group v-model="editForm.org_priority">
						<el-radio class="radio" :label="1">一</el-radio>
						<el-radio class="radio" :label="2">二</el-radio>
						<el-radio class="radio" :label="3">三</el-radio>
            <el-radio class="radio" :label="4">四</el-radio>
						<el-radio class="radio" :label="5">五</el-radio>
            <el-radio class="radio" :label="6">六</el-radio>
            <el-radio class="radio" :label="7">七</el-radio>
						<el-radio class="radio" :label="8">八</el-radio>
            <el-radio class="radio" :label="9">九</el-radio>
						<el-radio class="radio" :label="10">十</el-radio>
					</el-radio-group>
				</el-form-item>
        <el-form-item label="专家关键字" >
					<el-input v-model="editForm.org_keyword" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="媒体归属人" >
					<el-input v-model="editForm.org_belong" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="归属人联系方式" >
					<el-input v-model="editForm.org_contacts" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

		<!--新增界面-->
		<el-dialog title="新增" v-model="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
        <el-form-item label="组织名字" >
					<el-input v-model="addForm.orgName" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="身份简介" >
					<el-input v-model="addForm.orgIntroduction" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="宣传语" >
					<el-input v-model="addForm.orgMotto" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="组织类型">
          <el-select v-model="addForm.orgType" placeholder="请选择组织类型">
						<el-option label="公众号" value="1"></el-option>
						<el-option label="名人" value="2"></el-option>
            <el-option label="微博" value="3"></el-option>
            <el-option label="微信" value="4"></el-option>
            <el-option label="QQ" value="5"></el-option>
            <el-option label="其他" value="6"></el-option>
					</el-select>
        </el-form-item>
        <el-form-item label="推荐指数">
					<el-radio-group v-model="addForm.orgRecommandIndex">
						<el-radio class="radio" :label="1">一</el-radio>
						<el-radio class="radio" :label="2">二</el-radio>
						<el-radio class="radio" :label="3">三</el-radio>
            <el-radio class="radio" :label="4">四</el-radio>
						<el-radio class="radio" :label="5">五</el-radio>
            <el-radio class="radio" :label="6">六</el-radio>
            <el-radio class="radio" :label="7">七</el-radio>
						<el-radio class="radio" :label="8">八</el-radio>
            <el-radio class="radio" :label="9">九</el-radio>
						<el-radio class="radio" :label="10">十</el-radio>
					</el-radio-group>
				</el-form-item>
        <el-form-item label="优先级别">
					<el-radio-group v-model="addForm.orgPriority">
						<el-radio class="radio" :label="1">一</el-radio>
						<el-radio class="radio" :label="2">二</el-radio>
						<el-radio class="radio" :label="3">三</el-radio>
            <el-radio class="radio" :label="4">四</el-radio>
						<el-radio class="radio" :label="5">五</el-radio>
            <el-radio class="radio" :label="6">六</el-radio>
            <el-radio class="radio" :label="7">七</el-radio>
						<el-radio class="radio" :label="8">八</el-radio>
            <el-radio class="radio" :label="9">九</el-radio>
						<el-radio class="radio" :label="10">十</el-radio>
					</el-radio-group>
				</el-form-item>
        <el-form-item label="专家关键字" >
					<el-input v-model="addForm.orgKeyword" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="媒体归属人" >
					<el-input v-model="addForm.orgBelong" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="归属人联系方式" >
					<el-input v-model="addForm.orgContacts" auto-complete="off"></el-input>
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
import * as OrgMethods from "../../api/api";

export default {
  data() {
    return {
      filters: {
        name: ""
      },
      orgnizations: [],
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
        org_id: "",
        org_setupdate: "",
        org_setuptime: "",
        org_name: "",
        org_pic: "",
        org_qrpic: "",
        org_introduction: "",
        org_motto: "",
        org_type: "",
        org_recommandindex: "",
        org_priority: "",
        org_keyword: "",
        org_belong: "",
        org_contacts: ""
      },

      addFormVisible: false, //新增界面是否显示
      addLoading: false,
      addFormRules: {
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }]
      },
      //新增界面数据
      addForm: {
        orgId: "",
        orgSetupDate: "",
        orgSetupTime: "",
        orgName: "",
        orgPic: "",
        orgQRPic: "",
        orgIntroduction: "",
        orgMotto: "",
        orgType: "",
        orgRecommandIndex: "",
        orgPriority: "",
        orgKeyword: "",
        orgBelong: "",
        orgContacts: ""
      }
    };
  },
  methods: {
    //新增媒体机构
    addSubmit: function() {
      this.$refs.addForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            this.addLoading = true;
            let para = Object.assign({}, this.addForm);
            OrgMethods.addOrg(para).then(res => {
              this.addLoading = false;
              this.$message({
                message: "提交成功",
                type: "success"
              });
              this.$refs["addForm"].resetFields();
              this.addFormVisible = false;
              this.getOrgs();
            });
          });
        }
      });
    },
    //删除媒体机构
    handleDel: function(index, row) {
      this.$confirm("确认删除该记录吗?", "提示", {
        type: "warning"
      })
        .then(() => {
          this.listLoading = true;
          let para = row.org_id;
          OrgMethods.removeOrg(para).then(res => {
            this.listLoading = false;
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getOrgs();
          });
        })
        .catch(() => {});
    },
    //批量删除媒体机构
    batchRemove: function() {
      var ids = this.sels.map(item => item.org_id).toString();
      this.$confirm("确认删除选中记录吗？", "提示", {
        type: "warning"
      })
        .then(() => {
          this.listLoading = true;
          let para = { ids: ids };
          OrgMethods.batchRemoveOrg(para).then(res => {
            this.listLoading = false;
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getOrgs();
          });
        })
        .catch(() => {});
    },
    //编辑媒体机构
    editSubmit: function() {
      this.$refs.editForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            this.editLoading = true;
            let para = Object.assign({}, this.editForm);
            OrgMethods.editOrg(para).then(res => {
              this.editLoading = false;
              this.$message({
                message: "提交成功",
                type: "success"
              });
              this.$refs["editForm"].resetFields();
              this.editFormVisible = false;
              this.getOrgs();
            });
          });
        }
      });
    },
    //获取媒体机构列表
    getOrgs() {
      let para = {
        page: this.page,
        name: this.filters.name
      };
      this.listLoading = true;
      OrgMethods.getOrgList(para).then(res => {
        this.total = res.data.result.length;
        this.orgnizations = res.data.result;
        this.listLoading = false;
      });
    },

    handleCurrentChange(val) {
      this.page = val;
      this.getOrgs();
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
    }
  },
  mounted() {
    this.getOrgs();
  }
};
</script>

<style scoped>
</style>