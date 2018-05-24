<template>
	<section>
		<!--工具条-->
		<el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
			<el-form :inline="true" :model="filters">
				<el-form-item>
					<el-input v-model="filters.name" placeholder="姓名"></el-input>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" v-on:click="getBrds">查询</el-button>
				</el-form-item>
				<el-form-item>
					<el-button type="primary" @click="handleAdd">新增</el-button>
				</el-form-item>
			</el-form>
		</el-col>

		<!--列表-->
		<el-table :data="boards" highlight-current-row v-loading="listLoading" @selection-change="selsChange" style="width: 100%;">
			<el-table-column type="selection" width="55">
			</el-table-column >
			<el-table-column type="index" label="序号" width="80">
			</el-table-column>
			<el-table-column prop="brdId" label="公告ID" width="120" v-if="false">
			</el-table-column>
			<el-table-column prop="brdSetupDate" label="创建日期" width="115" sortable>
			</el-table-column>
			<el-table-column prop="brdSetupTime" label="创建时间" width="115" sortable>
			</el-table-column>
			<el-table-column prop="rcmDate" label="推荐日期" width="115" sortable>
			</el-table-column>
			<el-table-column prop="rcmTime" label="推荐时间" width="115" sortable>
			</el-table-column>
			<el-table-column prop="rcmContent" label="推荐内容" width="150" >
			</el-table-column>
			<el-table-column prop="rcmReason" label="推荐理由" width="250" >
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
				<el-form-item label="预告时间">
					<el-col :span="11">
						<el-date-picker type="date" placeholder="选择日期" v-model="editForm.rcmDate" style="width: 100%;"></el-date-picker>
					</el-col>
					<el-col class="line" :span="2">-</el-col>
					<el-col :span="11">
						<el-time-picker type="fixed-time" placeholder="选择时间" v-model="editForm.rcmTime" style="width: 100%;"></el-time-picker>
					</el-col>
				</el-form-item>
				<el-form-item label="推荐内容" >
					<el-input v-model="editForm.rcmContent" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="推荐理由" >
					<el-input v-model="editForm.rcmReason" auto-complete="off"></el-input>
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
				<el-form-item label="预告时间">
					<el-col :span="11">
						<el-date-picker type="date" placeholder="选择日期" v-model="editForm.rcmDate" style="width: 100%;"></el-date-picker>
					</el-col>
					<el-col class="line" :span="2">-</el-col>
					<el-col :span="11">
						<el-time-picker type="fixed-time" placeholder="选择时间" v-model="editForm.rcmTime" style="width: 100%;"></el-time-picker>
					</el-col>
				</el-form-item>
				<el-form-item label="推荐内容" >
					<el-input v-model="editForm.rcmContent" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="推荐理由" >
					<el-input v-model="editForm.rcmReason" auto-complete="off"></el-input>
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
import * as BrdMethods from "../../api/api";

export default {
  data() {
    return {
      filters: {
        name: ""
      },
      boards: [],
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
        rcmDate: "",
        rcmTime: "",
        rcmContent: "",
        rcmReason: ""
      },

      addFormVisible: false, //新增界面是否显示
      addLoading: false,
      addFormRules: {
        name: [{ required: true, message: "请输入姓名", trigger: "blur" }]
      },
      //新增界面数据
      addForm: {
        rcmDate: "",
        rcmTime: "",
        rcmContent: "",
        rcmReason: ""
      }
    };
  },
  mounted() {
    this.getBrds();
  },
  methods: {
    //新增公告
    addSubmit: function() {
      this.$refs.addForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            this.addLoading = true;
            let para = Object.assign({}, this.addForm);
            BrdMethods.addBrd(para).then(res => {
              this.addLoading = false;
              this.$message({
                message: "提交成功",
                type: "success"
              });
              this.$refs["addForm"].resetFields();
              this.addFormVisible = false;
              this.getBrds();
            });
          });
        }
      });
    },

    //删除公告
    handleDel: function(index, row) {
      this.$confirm("确认删除该记录吗?", "提示", {
        type: "warning"
      })
        .then(() => {
          this.listLoading = true;
          let para = { id: row.id };
          BrdMethods.removeBrd(para).then(res => {
            this.listLoading = false;
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getBrds();
          });
        })
        .catch(() => {});
    },

    //批量删除公告
    batchRemove: function() {
      var ids = this.sels.map(item => item.id).toString();
      this.$confirm("确认删除选中记录吗？", "提示", {
        type: "warning"
      })
        .then(() => {
          this.listLoading = true;
          let para = { ids: ids };
          BrdMethods.batchRemoveBrd(para).then(res => {
            this.listLoading = false;
            this.$message({
              message: "删除成功",
              type: "success"
            });
            this.getBrds();
          });
        })
        .catch(() => {});
    },

    //修改公告
    editSubmit: function() {
      this.$refs.editForm.validate(valid => {
        if (valid) {
          this.$confirm("确认提交吗？", "提示", {}).then(() => {
            this.editLoading = true;
            let para = Object.assign({}, this.editForm);
            BrdMethods.editBrd(para).then(res => {
              this.editLoading = false;
              this.$message({
                message: "提交成功",
                type: "success"
              });
              this.$refs["editForm"].resetFields();
              this.editFormVisible = false;
              this.getBrds();
            });
          });
        }
      });
    },
    //获取公告列表
    getBrds() {
      let para = {
        page: this.page,
        name: this.filters.name
      };
      this.listLoading = true;
      BrdMethods.getBrdListPage(para).then(res => {
        this.total = res.data.total;
        this.boards = res.data.boards;
        this.listLoading = false;
      });
    },
    handleCurrentChange(val) {
      this.page = val;
      this.getBrds();
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
  }
};
</script>

<style scoped>
</style>