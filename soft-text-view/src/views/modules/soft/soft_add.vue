<template>
  <div class="app-container pull-auto">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" label-width="80px">
      <el-steps :active="active" simple class="padding-top:10px">
        <el-step title="上传稿件" icon="el-icon-edit"></el-step>
        <el-step title="选择媒体" icon="el-icon-edit-outline"></el-step>
        <el-step title="提交稿件" icon="el-icon-loading"></el-step>
      </el-steps>
      <el-row style="margin: 30px;" v-show="active === 0">
        <el-row>
          <el-tooltip class="item" effect="dark" content="标题中适当的植入一些长尾关键词、热门关键词，比较利于SEO哦！" placement="bottom-end">
            <el-form-item label="软文标题" prop="title">
              <el-input v-model="dataForm.title" placeholder="一个吸引人的标题，是软文成功的一半！"></el-input>
            </el-form-item>
          </el-tooltip>
        </el-row>
        <el-row>
          <el-form-item label="上传文档" prop="charg">
            <el-upload
              class="upload-demo"
              action="#"
              :data="uplaodData"
              :before-upload="beforeAvatarUpload"
              :on-remove="handleRemove"
              :http-request="uploadFile"
              :file-list="fileList"
              :limit="1">
              <el-button size="small" type="primary">点击上传word文档</el-button>
            </el-upload>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="内容" prop="content">
            <avue-ueditor v-model="dataForm.content"></avue-ueditor>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="会员标签" prop="membertags">
            <el-input v-model="dataForm.membertags" placeholder="可选填，用来区分用户或者其他标签"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="您的要求" prop="remarkstype">
            <el-radio-group v-model="dataForm.remarkstype">
              <el-radio :label="1">没要求（推荐）</el-radio>
              <el-radio :label="2">按原稿发布</el-radio>
              <el-radio :label="3">按下面附言的要求处理</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="发稿时间" prop="publictime">
            <el-radio-group v-model="dataForm.publictime">
              <el-radio :label="1">2天内完成（推荐）</el-radio>
              <el-radio :label="2">当天完成</el-radio>
              <el-radio :label="3">一周内完成</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="附言" prop="remark">
            <el-input type="textarea" :rows="3" v-model="dataForm.remark" placeholder="可选填"></el-input>
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="注意事项">
            <el-row>1、稿件内容必须属于正面信息，如有负面、涉政、敏感等内容一律不予发布并停止账号使用。</el-row>
            <el-row>2，稿件发布之后不可修改、取消和删除，请在发布前确认发布稿件的内容。</el-row>
            <el-row>3、所有已经发布成功的稿件，保证二个月的链接时效性，若期间遇到文章被删除或内容被替换，可联系我们退款或重发。</el-row>
            <el-row>4、若软文存在违法、负面、涉嫌虚假宣传、政治敏感的内容及链接，因网站主管单位检查，被编辑人员删除的，本平台不予退款。</el-row>
            <el-row>5、所有媒体网址、署名、电话、QQ、微信、二维码等联系方式，一律不保证，也不保证100%收录。</el-row>
            <el-row>6、工作时间： 周一至周六 09:00-18:00，部分网站晚上和周六日也可出稿，下午17点后提交的文章在隔天发布。</el-row>
          </el-form-item>
        </el-row>
        <div class="dialog-footer" style="text-align: center">
          <el-button @click="tempSumbit()">保存草稿</el-button>
          <el-button type="primary" @click="dataFormSubmit1()">下一步</el-button>
        </div>
      </el-row>
      <el-row style="margin: 30px" v-show="active === 1">
        <el-form :inline="true" :model="dataForm" @keyup.enter.native="getDataList()">
          <el-collapse>
            <el-collapse-item title="条件筛选">
              <el-row>
                <el-col :span="8">
                  <el-form-item label="频道类型">
                    <el-input v-model="dataForm.ebeln" clearable></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="综合门户">
                    <el-input v-model="dataForm.ebeln" clearable></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="所在地区">
                    <el-input v-model="dataForm.ebeln" clearable></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="8">
                  <el-form-item label="特别行业">
                    <el-input v-model="dataForm.ebeln" clearable></el-input>
                  </el-form-item>
                </el-col>
                <el-button type="primary" @click="getMediaList()">查询</el-button>
              </el-row>
            </el-collapse-item>
          </el-collapse>
        </el-form>
        <el-table
          :data="dataList"
          border
          v-loading="dataListLoading"
          :cell-style="cellStyle"
          :default-sort="{prop: 'price', order: 'descending'}"
          @selection-change="selectionChangeHandle"
          style="width: 100%;">
          <el-table-column
            type="selection"
            header-align="center"
            align="center"
            width="50">
          </el-table-column>
          <el-table-column
            prop="medianame"
            header-align="center"
            align="center"
            label="媒体名称">
          </el-table-column>
          <el-table-column
            prop="mediatype"
            header-align="center"
            align="center"
            label="媒体类型">
          </el-table-column>
          <el-table-column
            prop="area"
            header-align="center"
            align="center"
            label="地区">
          </el-table-column>
          <el-table-column
            sortable
            prop="price"
            header-align="center"
            align="center"
            label="价格">
          </el-table-column>
          <el-table-column
            prop="weights"
            header-align="center"
            align="center"
            label="权重">
          </el-table-column>
          <el-table-column
            prop="extrainfo"
            header-align="center"
            align="center"
            label="可带广告信息">
          </el-table-column>
          <el-table-column
            prop="remake"
            header-align="center"
            align="center"
            label="备注">
          </el-table-column>
        </el-table>
        <el-pagination
          @size-change="sizeChangeHandle"
          @current-change="currentChangeHandle"
          :current-page="pageIndex"
          :page-sizes="[10, 20, 50, 100]"
          :page-size="pageSize"
          :total="totalPage"
          layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>

        <div class="dialog-footer" style="padding:20px;text-align: center">
          <el-button type="primary" @click="cancelData()">上一步</el-button>
          <el-button type="primary" @click="dataFormSubmit2()">下一步预览并提交订单</el-button>
        </div>
        <el-card class="card" v-if="this.dataListSelections.length > 0">
          <el-table
            :data="dataListSelections"
            :summary-method="getSummaries"
            show-summary
            style="width: 100%">
            <el-table-column
              type="index"
              label="序号"
              width="50">
            </el-table-column>
            <el-table-column
              prop="medianame"
              label="媒体名称"
              header-align="center"
              align="center">
            </el-table-column>
            <el-table-column
              prop="price"
              label="价格"
              header-align="center"
              align="center">
            </el-table-column>
          </el-table>
        </el-card>
      </el-row>
      <el-row style="margin: 30px;" v-show="active === 2">

        <el-form-item label="总费用" prop="sumPrice">
          {{dataForm.sumPrice}}
        </el-form-item>
        <el-form-item label="账户余额" prop="title">
          <el-row v-model="dataForm.title"></el-row>
        </el-form-item>
        <el-form-item label="软文标题" prop="title">
          {{dataForm.title}}
        </el-form-item>
        <el-form-item label="内容" prop="content">
          <p v-html="dataForm.content"></p>
        </el-form-item>
        <el-form-item label="会员标签">
          {{dataForm.membertags}}
        </el-form-item>
        <el-form-item label="您的要求" prop="remarkstype">
          <span v-if="dataForm.remarkstype === 1">没要求</span>
          <span v-if="dataForm.remarkstype === 2">按原稿发布</span>
          <span v-if="dataForm.remarkstype === 3">按下面附言的要求处理</span>
        </el-form-item>
        <el-form-item label="发稿时间" prop="publictime">
          <span v-if="dataForm.publictime === 1">2天内完成</span>
          <span v-if="dataForm.publictime === 2">当天完成</span>
          <span v-if="dataForm.publictime === 3">一周内完成</span>
        </el-form-item>
        <el-row>
          <el-form-item label="附言" prop="remark">
            {{dataForm.remark}}
          </el-form-item>
        </el-row>
        <el-row>
          <el-form-item label="注意事项">
            <el-row>1、稿件内容必须属于正面信息，如有负面、涉政、敏感等内容一律不予发布并停止账号使用。</el-row>
            <el-row>2，稿件发布之后不可修改、取消和删除，请在发布前确认发布稿件的内容。</el-row>
            <el-row>3、所有已经发布成功的稿件，保证二个月的链接时效性，若期间遇到文章被删除或内容被替换，可联系我们退款或重发。</el-row>
            <el-row>4、若软文存在违法、负面、涉嫌虚假宣传、政治敏感的内容及链接，因网站主管单位检查，被编辑人员删除的，本平台不予退款。</el-row>
            <el-row>5、所有媒体网址、署名、电话、QQ、微信、二维码等联系方式，一律不保证，也不保证100%收录。</el-row>
            <el-row>6、工作时间： 周一至周六 09:00-18:00，部分网站晚上和周六日也可出稿，下午17点后提交的文章在隔天发布。</el-row>
          </el-form-item>
        </el-row>
        <div class="dialog-footer" style="text-align: center">
          <el-button @click="cancelData()">上一步</el-button>
          <el-button type="success" @click="dataFormSubmit3()">提交订单</el-button>
        </div>
      </el-row>
    </el-form>
  </div>
</template>

<script>
  import Vue from 'vue'

  export default {
    data() {
      return {
        pageIndex: 1,
        pageSize: 10,
        totalPage: 0,
        active: 0,   //步骤组件
        activeName: 'first',  //tab切换model
        stepFlag: false,
        dataListLoading: false,
        dataList: [],
        fileList: [],
        dataListSelections: [], //稿件信息
        uplaodData: null,
        dataForm: {
          title: '',
          content: '',
          remarkstype: 1,
          membertags: '',
          publictime: 1,
          remark: '',
          sumPrice:'',
        },
        dataRule: {
          title: [
            {required: true, message: '标题不能为空', trigger: 'blur'},
            {max: 100, message: '长度在100个字符之内', trigger: 'blur'}
          ],
          content: [
            {required: true, message: '内容不能为空', trigger: 'blur'},
          ],
          remark: [
            {max: 500, message: '长度在500个字符之内', trigger: 'blur'}
          ]
        },
      }
    },
    created() {
    },
    methods: {
      tempSumbit() {
        if (this.dataForm.title === null || this.dataForm.title === undefined || this.dataForm.title === '') {
          const h = this.$createElement;
          this.$notify({
            title: '软文标题',
            message: h('i', {style: 'color: teal'}, '软文标题不能为空'),
            type: 'warning'
          });
        } else if (this.dataForm.content === null || this.dataForm.content === undefined || this.dataForm.content === '') {
          const h = this.$createElement;
          this.$notify({
            title: '稿件内容',
            message: h('i', {style: 'color: teal'}, '内容不能为空'),
            type: 'warning'
          });
        } else {
          this.$http({
            url: this.$http.adornUrl('/generator/softtext/tempSave'),
            method: 'post',
            data: this.$http.adornData({
              'title': this.dataForm.title,
              'content': this.dataForm.content,
              'membertags': this.dataForm.membertags,
              'remarkstype': this.dataForm.remarkstype,
              'publictime': this.dataForm.publictime,
              'remark': this.dataForm.remark,
            })
          }).then(({data}) => {
            if (data && data.code === 0) {
              this.dataForm.title = '',
                this.dataForm.content = '',
                this.dataForm.membertags = '',
                this.dataForm.remark = '',
                this.dataForm.remarkstype = 1,
                this.dataForm.publictime = 1,
                this.uplaodData = null,
                this.fileList = [],
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500
                })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      },
      dataFormSubmit1() {
        if (this.dataForm.title === null || this.dataForm.title === undefined || this.dataForm.title === '') {
          const h = this.$createElement;
          this.$notify({
            title: '软文标题',
            message: h('i', {style: 'color: teal'}, '软文标题不能为空'),
            type: 'warning'
          });
        } else if (this.dataForm.content === null || this.dataForm.content === undefined || this.dataForm.content === '') {
          const h = this.$createElement;
          this.$notify({
            title: '稿件内容',
            message: h('i', {style: 'color: teal'}, '内容不能为空'),
            type: 'warning'
          });
        } else {
          this.stepFlag = true,
            this.next()
        }
      },
      dataFormSubmit2() {
        this.stepFlag = true,
          this.next()
      },
      dataFormSubmit3(){
        this.$http({
          url: this.$http.adornUrl('/generator/softtext/save'),
          method: 'post',
          data: this.$http.adornData({
            'title': this.dataForm.title,
            'content': this.dataForm.content,
            'membertags': this.dataForm.membertags,
            'remarkstype': this.dataForm.remarkstype,
            'publictime': this.dataForm.publictime,
            'remark': this.dataForm.remark,
            'sumPrice':this.dataForm.sumPrice,
          })
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataForm.title = '',
              this.dataForm.content = '',
              this.dataForm.membertags = '',
              this.dataForm.remark = '',
              this.dataForm.remarkstype = 1,
              this.dataForm.publictime = 1,
              this.dataForm.sumPrice = 0,
              this.fileList = [],
              this.uplaodData = null,
              this.active = 0,
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500
              })
          } else {
            this.$message.error(data.msg)
          }
        })
      },
      //获取媒体列表
      getMediaList() {
        this.$http({
          url: this.$http.adornUrl('/generator/media/list'),
          method: 'get',
          data: this.$http.adornParams({})
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.dataList = data.page.list
            this.totalPage = data.page.totalCount
          } else {
            this.dataList = []
            this.totalPage = 0
          }
        })
      },
      //step组件步骤
      next() {
        if (this.stepFlag) {
          if (this.active++ > 2) this.active = 0;
          if (this.active === 1) this.getMediaList();
          console.log('next')
          console.log(this.dataForm.sumPrice)
          this.stepFlag = false
        }
      },
      //返回上一步
      cancelData() {
        this.stepFlag = false;
        this.active--;
      },
      // 每页数
      sizeChangeHandle(val) {
        this.pageSize = val
        this.pageIndex = 1
      },
      // 当前页
      currentChangeHandle(val) {
        this.pageIndex = val
      },
      //上传前限制
      beforeAvatarUpload(file) {
        const isDocxWord = file.type === 'application/vnd.openxmlformats-officedocument.wordprocessingml.document';
        const idDocWord = file.type === 'application/msword';
        const isLt5M = file.size / 1024 / 1024 < 5;

        if (!(isDocxWord || idDocWord)) {
          this.$message.error('上传文件只能是 doc/docx 格式!');
        }
        if (!isLt5M) {
          this.$message.error('上传文档大小不能超过 5MB!');
        }
        return isDocxWord && isLt5M;
      },

      handleRemove(file, fileList) {
        file = null,
        fileList = []
      },

      //自定义上传附件
      uploadFile(uplaodData) {
        let me = this
        let loadingInstance1 = me.$loading({fullscreen: true, text: '解析中...'})
        me.uploadFilew(uplaodData, loadingInstance1)
      },
      uploadFilew(uplaodData, loadingInstance1) {
        let me = this;
        me.dataForm.content = '';
        var file = uplaodData.file;
        var formData = new FormData();
        formData.append('file', file);//传文件
        $.ajax({
          headers: {token: Vue.cookie.get('token')},
          url: me.$http.adornUrl(`/generator/softtext/file`),
          type: 'POST',
          data: formData,
          async: true, //异步
          contentType: "multipart/form-data",
          contentType: false,
          processData: false, //很重要，告诉jquery不要对form进行处理
          //这儿的三个参数其实就是XMLHttpRequest里面带的信息。
          success: function (data, a1, a2) {
            loadingInstance1.close()
            if (a1 == 'success') {
              me.dataForm.content = data.msg;
            }
          }
        })
      },
      cellStyle(data) {
        if (data.columnIndex === 0) {
          return 'background: #CCCCCC'
        }
      },
      // 多选媒体
      selectionChangeHandle(val) {
        this.dataListSelections = val
      },
      //总数
      getSummaries(param) {
        const me = this;
        const { columns, data } = param;
        const sums = [];
        columns.forEach((column, index) => {
          if (index === 0) {
            sums[index] = '总价';
            return;
          }
          const values = data.map(item => Number(item[column.property]));
          if (!values.every(value => isNaN(value))) {
            sums[index] = values.reduce((prev, curr) => {
              const value = Number(curr);
              if (!isNaN(value)) {
                return prev + curr;
              } else {
                return prev;
              }
            }, 0);
            me.dataForm.sumPrice = (sums[index] += ' 元');
            console.log(me.dataForm.sumPrice)
          } else {
            sums[index] = 'N/A';
          }
        });
        return sums;
      },
    },

    mounted() {
    }
  }
</script>
