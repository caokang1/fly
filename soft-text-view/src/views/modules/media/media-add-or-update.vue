<!--<template>-->
<!--  <el-dialog-->
<!--    :title="!dataForm.id ? '新增' : '修改'"-->
<!--    :close-on-click-modal="false"-->
<!--    :visible.sync="visible">-->
<!--    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">-->
<!--    <el-form-item label="媒体名称" prop="medianame">-->
<!--      <el-input v-model="dataForm.medianame" placeholder="媒体名称"></el-input>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="" prop="mediatype">-->
<!--      <el-input v-model="dataForm.mediatype" placeholder=""></el-input>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="地区" prop="area">-->
<!--      <el-input v-model="dataForm.area" placeholder="地区"></el-input>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="价格" prop="price">-->
<!--      <el-input v-model="dataForm.price" placeholder="价格"></el-input>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="权重" prop="weights">-->
<!--      <el-input v-model="dataForm.weights" placeholder="权重"></el-input>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="可带广告信息（网址，电话，微信qq，二维码，其他）" prop="extrainfo">-->
<!--      <el-input v-model="dataForm.extrainfo" placeholder="可带广告信息（网址，电话，微信qq，二维码，其他）"></el-input>-->
<!--    </el-form-item>-->
<!--    <el-form-item label="备注" prop="remake">-->
<!--      <el-input v-model="dataForm.remake" placeholder="备注"></el-input>-->
<!--    </el-form-item>-->
<!--    </el-form>-->
<!--    <span slot="footer" class="dialog-footer">-->
<!--      <el-button @click="visible = false">取消</el-button>-->
<!--      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>-->
<!--    </span>-->
<!--  </el-dialog>-->
<!--</template>-->

<!--<script>-->
<!--  export default {-->
<!--    data () {-->
<!--      return {-->
<!--        visible: false,-->
<!--        dataForm: {-->
<!--          id: 0,-->
<!--          medianame: '',-->
<!--          mediatype: '',-->
<!--          area: '',-->
<!--          price: '',-->
<!--          weights: '',-->
<!--          extrainfo: '',-->
<!--          remake: ''-->
<!--        },-->
<!--        dataRule: {-->
<!--          medianame: [-->
<!--            { required: true, message: '媒体名称不能为空', trigger: 'blur' }-->
<!--          ],-->
<!--          mediatype: [-->
<!--            { required: true, message: '不能为空', trigger: 'blur' }-->
<!--          ],-->
<!--          area: [-->
<!--            { required: true, message: '地区不能为空', trigger: 'blur' }-->
<!--          ],-->
<!--          price: [-->
<!--            { required: true, message: '价格不能为空', trigger: 'blur' }-->
<!--          ],-->
<!--          weights: [-->
<!--            { required: true, message: '权重不能为空', trigger: 'blur' }-->
<!--          ],-->
<!--          extrainfo: [-->
<!--            { required: true, message: '可带广告信息（网址，电话，微信qq，二维码，其他）不能为空', trigger: 'blur' }-->
<!--          ],-->
<!--          remake: [-->
<!--            { required: true, message: '备注不能为空', trigger: 'blur' }-->
<!--          ]-->
<!--        }-->
<!--      }-->
<!--    },-->
<!--    methods: {-->
<!--      init (id) {-->
<!--        this.dataForm.id = id || 0-->
<!--        this.visible = true-->
<!--        this.$nextTick(() => {-->
<!--          this.$refs['dataForm'].resetFields()-->
<!--          if (this.dataForm.id) {-->
<!--            this.$http({-->
<!--              url: this.$http.adornUrl(`/generator/media/info/${this.dataForm.id}`),-->
<!--              method: 'get',-->
<!--              params: this.$http.adornParams()-->
<!--            }).then(({data}) => {-->
<!--              if (data && data.code === 0) {-->
<!--                this.dataForm.medianame = data.media.medianame-->
<!--                this.dataForm.mediatype = data.media.mediatype-->
<!--                this.dataForm.area = data.media.area-->
<!--                this.dataForm.price = data.media.price-->
<!--                this.dataForm.weights = data.media.weights-->
<!--                this.dataForm.extrainfo = data.media.extrainfo-->
<!--                this.dataForm.remake = data.media.remake-->
<!--              }-->
<!--            })-->
<!--          }-->
<!--        })-->
<!--      },-->
<!--      // 表单提交-->
<!--      dataFormSubmit () {-->
<!--        this.$refs['dataForm'].validate((valid) => {-->
<!--          if (valid) {-->
<!--            this.$http({-->
<!--              url: this.$http.adornUrl(`/generator/media/${!this.dataForm.id ? 'save' : 'update'}`),-->
<!--              method: 'post',-->
<!--              data: this.$http.adornData({-->
<!--                'id': this.dataForm.id || undefined,-->
<!--                'medianame': this.dataForm.medianame,-->
<!--                'mediatype': this.dataForm.mediatype,-->
<!--                'area': this.dataForm.area,-->
<!--                'price': this.dataForm.price,-->
<!--                'weights': this.dataForm.weights,-->
<!--                'extrainfo': this.dataForm.extrainfo,-->
<!--                'remake': this.dataForm.remake-->
<!--              })-->
<!--            }).then(({data}) => {-->
<!--              if (data && data.code === 0) {-->
<!--                this.$message({-->
<!--                  message: '操作成功',-->
<!--                  type: 'success',-->
<!--                  duration: 1500,-->
<!--                  onClose: () => {-->
<!--                    this.visible = false-->
<!--                    this.$emit('refreshDataList')-->
<!--                  }-->
<!--                })-->
<!--              } else {-->
<!--                this.$message.error(data.msg)-->
<!--              }-->
<!--            })-->
<!--          }-->
<!--        })-->
<!--      }-->
<!--    }-->
<!--  }-->
<!--</script>-->
