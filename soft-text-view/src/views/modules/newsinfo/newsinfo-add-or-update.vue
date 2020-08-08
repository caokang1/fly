<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="新闻标题" prop="newsTitle">
      <el-input v-model="dataForm.newsTitle" placeholder="新闻标题"></el-input>
    </el-form-item>
    <el-form-item label="新闻图片" prop="newsPicture">
      <el-upload
        class="avatar-uploader"
        action="https://jsonplaceholder.typicode.com/posts/"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload">
        <img v-if="imageUrl" :src="imageUrl" class="avatar">
        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
      </el-upload>
    </el-form-item>
    <el-form-item label="新闻内容" prop="newsContent">
      <avue-ueditor v-model="dataForm.content"></avue-ueditor>
    </el-form-item>
    <el-form-item label="新闻类型" prop="newsTypeCode">
      <el-input v-model="dataForm.newsTypeCode" placeholder="新闻类型"></el-input>
    </el-form-item>
    <el-form-item label="是否发布（1=是，0=否）" prop="isPublish">
      <el-input v-model="dataForm.isPublish" placeholder="是否发布（1=是，0=否）"></el-input>
    </el-form-item>
    <el-form-item label="发布时间" prop="publishTime">
      <el-input v-model="dataForm.publishTime" placeholder="发布时间"></el-input>
    </el-form-item>
    <el-form-item label="置顶状态（0=顶，1=推，2=精,  3=普通）" prop="topStatus">
      <el-input v-model="dataForm.topStatus" placeholder="置顶状态（0=顶，1=推，2=精,  3=普通）"></el-input>
    </el-form-item>
    <el-form-item label="是否原创(1=是，0=否)" prop="isOriginal">
      <el-input v-model="dataForm.isOriginal" placeholder="是否原创(1=是，0=否)"></el-input>
    </el-form-item>
    <el-form-item label="点击量" prop="clickRate">
      <el-input v-model="dataForm.clickRate" placeholder="点击量"></el-input>
    </el-form-item>
    <el-form-item label="发布人ID" prop="publishId">
      <el-input v-model="dataForm.publishId" placeholder="发布人ID"></el-input>
    </el-form-item>
    <el-form-item label="发布人" prop="publisher">
      <el-input v-model="dataForm.publisher" placeholder="发布人"></el-input>
    </el-form-item>
    <el-form-item label="创建人ID" prop="createId">
      <el-input v-model="dataForm.createId" placeholder="创建人ID"></el-input>
    </el-form-item>
    <el-form-item label="创建人姓名" prop="creater">
      <el-input v-model="dataForm.creater" placeholder="创建人姓名"></el-input>
    </el-form-item>
    <el-form-item label="更新人ID" prop="updateId">
      <el-input v-model="dataForm.updateId" placeholder="更新人ID"></el-input>
    </el-form-item>
    <el-form-item label="更新人" prop="updater">
      <el-input v-model="dataForm.updater" placeholder="更新人"></el-input>
    </el-form-item>
    <el-form-item label="创建时间" prop="createTime">
      <el-input v-model="dataForm.createTime" placeholder="创建时间"></el-input>
    </el-form-item>
    <el-form-item label="" prop="lastUpdateTime">
      <el-input v-model="dataForm.lastUpdateTime" placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="删除标记" prop="delFlag">
      <el-input v-model="dataForm.delFlag" placeholder="删除标记"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        imageUrl: '',
        dataForm: {
          id: 0,
          newsTitle: '',
          newsPicture: '',
          newsContent: '',
          newsTypeCode: '',
          isPublish: '',
          publishTime: '',
          topStatus: '',
          isOriginal: '',
          clickRate: '',
          publishId: '',
          publisher: '',
          createId: '',
          creater: '',
          updateId: '',
          updater: '',
          createTime: '',
          lastUpdateTime: '',
          delFlag: ''
        },
        dataRule: {
          newsTitle: [
            { required: true, message: '新闻标题不能为空', trigger: 'blur' }
          ],
          newsPicture: [
            { required: true, message: '新闻图片不能为空', trigger: 'blur' }
          ],
          newsContent: [
            { required: true, message: '新闻内容不能为空', trigger: 'blur' }
          ],
          newsTypeCode: [
            { required: true, message: '新闻类型不能为空', trigger: 'blur' }
          ],
          isPublish: [
            { required: true, message: '是否发布（1=是，0=否）不能为空', trigger: 'blur' }
          ],
          publishTime: [
            { required: true, message: '发布时间不能为空', trigger: 'blur' }
          ],
          topStatus: [
            { required: true, message: '置顶状态（0=顶，1=推，2=精,  3=普通）不能为空', trigger: 'blur' }
          ],
          isOriginal: [
            { required: true, message: '是否原创(1=是，0=否)不能为空', trigger: 'blur' }
          ],
          clickRate: [
            { required: true, message: '点击量不能为空', trigger: 'blur' }
          ],
          publishId: [
            { required: true, message: '发布人ID不能为空', trigger: 'blur' }
          ],
          publisher: [
            { required: true, message: '发布人不能为空', trigger: 'blur' }
          ],
          createId: [
            { required: true, message: '创建人ID不能为空', trigger: 'blur' }
          ],
          creater: [
            { required: true, message: '创建人姓名不能为空', trigger: 'blur' }
          ],
          updateId: [
            { required: true, message: '更新人ID不能为空', trigger: 'blur' }
          ],
          updater: [
            { required: true, message: '更新人不能为空', trigger: 'blur' }
          ],
          createTime: [
            { required: true, message: '创建时间不能为空', trigger: 'blur' }
          ],
          lastUpdateTime: [
            { required: true, message: '不能为空', trigger: 'blur' }
          ],
          delFlag: [
            { required: true, message: '删除标记不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/generator/newsinfo/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.newsTitle = data.newsinfo.newsTitle
                this.dataForm.newsPicture = data.newsinfo.newsPicture
                this.dataForm.newsContent = data.newsinfo.newsContent
                this.dataForm.newsTypeCode = data.newsinfo.newsTypeCode
                this.dataForm.isPublish = data.newsinfo.isPublish
                this.dataForm.publishTime = data.newsinfo.publishTime
                this.dataForm.topStatus = data.newsinfo.topStatus
                this.dataForm.isOriginal = data.newsinfo.isOriginal
                this.dataForm.clickRate = data.newsinfo.clickRate
                this.dataForm.publishId = data.newsinfo.publishId
                this.dataForm.publisher = data.newsinfo.publisher
                this.dataForm.createId = data.newsinfo.createId
                this.dataForm.creater = data.newsinfo.creater
                this.dataForm.updateId = data.newsinfo.updateId
                this.dataForm.updater = data.newsinfo.updater
                this.dataForm.createTime = data.newsinfo.createTime
                this.dataForm.lastUpdateTime = data.newsinfo.lastUpdateTime
                this.dataForm.delFlag = data.newsinfo.delFlag
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/generator/newsinfo/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'newsTitle': this.dataForm.newsTitle,
                'newsPicture': this.dataForm.newsPicture,
                'newsContent': this.dataForm.newsContent,
                'newsTypeCode': this.dataForm.newsTypeCode,
                'isPublish': this.dataForm.isPublish,
                'publishTime': this.dataForm.publishTime,
                'topStatus': this.dataForm.topStatus,
                'isOriginal': this.dataForm.isOriginal,
                'clickRate': this.dataForm.clickRate,
                'publishId': this.dataForm.publishId,
                'publisher': this.dataForm.publisher,
                'createId': this.dataForm.createId,
                'creater': this.dataForm.creater,
                'updateId': this.dataForm.updateId,
                'updater': this.dataForm.updater,
                'createTime': this.dataForm.createTime,
                'lastUpdateTime': this.dataForm.lastUpdateTime,
                'delFlag': this.dataForm.delFlag
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      },
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
    }
  }
</script>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
