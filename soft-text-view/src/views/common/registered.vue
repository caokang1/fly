<template>
  <div class="site-wrapper site-page--login">
    <div class="site-content__wrapper">
      <div class="site-content">
        <div class="brand-info">
          <h2 class="brand-info__text">软文系统管理平台</h2>
          <p class="brand-info__intro">为软文推广提供一套更优的解决方案。</p>
        </div>
        <div class="login-main">
          <h3 class="login-title">用户注册</h3>
          <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()"
                   status-icon>
            <el-form-item prop="userName">
              <el-input v-model="dataForm.userName" placeholder="请填写用户名"></el-input>
            </el-form-item>
            <el-form-item prop="userType">
              <el-radio-group v-model="dataForm.userType">
                <el-radio :label="3">普通用户</el-radio>
                <el-radio :label="4">资源方</el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item prop="phone">
              <el-input v-model="dataForm.phone" placeholder="请填写手机号"></el-input>
            </el-form-item>
            <el-form-item prop="captcha">
              <el-row :gutter="20">
                <el-col :span="14">
                  <el-input v-model="dataForm.captcha" placeholder="验证码">
                  </el-input>
                </el-col>
                <el-col :span="6">
                  <el-button type="primary" @click="dataFormSubmit()">发送验证码</el-button>
                </el-col>
              </el-row>
            </el-form-item>
            <el-form-item prop="password">
              <el-input v-model="dataForm.password" type="password" placeholder="登陆密码"></el-input>
            </el-form-item>
            <el-form-item prop="comfirmPassword">
              <el-input v-model="dataForm.comfirmPassword" type="password" placeholder="再次确认密码"></el-input>
            </el-form-item>
            <el-form-item prop="QQ">
              <el-input v-model="dataForm.QQ" placeholder="请填写QQ"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="registered()">马上注册</el-button>
              <el-button type="primary" @click="Cancel()">返回登录</el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      var validateuserName = (rule, value, callback) => {
        if (!/\S/.test(value)) {
          callback(new Error('用户名不能为空'))
        } else if (!/^[\u4e00-\u9fa5]*$/.test(value)) {
          callback(new Error('用户名包含非法字符'))
        } else {
          callback()
        }
      };
      var validatePhone = (rule, value, callback) => {
        if (!/\S/.test(value)) {
          callback(new Error('手机号不能为空'))
        } else if (!/(^1\d{10}$)|(^\d{3,4}-\d{7,8}$)|(^\d{7,8}$)|(^\d{3,4}-\d{7,8}-\d{1,4}$)|(^\d{7,8}-\d{1,4}$)/.test(value)) {
          callback(new Error('请输入正确的手机号'))
        } else {
          callback()
        }
      };
      var validatePassword = (rule, value, callback) => {
        if (!/\S/.test(value)) {
          callback(new Error('密码不能为空'))
        } else if (!/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[\s\S]{8,16}$/.test(value)) {
          callback(new Error('密码由8-16个大写字母，小写字母，数字组成'))
        } else {
          callback()
        }
      };
      var validateComfirmPassword = (rule, value, callback) => {
        if (!/\S/.test(value)) {
          callback(new Error('确认密码不能为空'))
        } else if (this.dataForm.password !== value) {
          callback(new Error('确认密码与密码输入不一致'))
        } else {
          callback()
        }
      };
      var validateQQ = (rule, value, callback) => {
        if (!value.match('[1-9]{1}[0-9]{4,11}')) {
          callback(new Error('请输入正确的QQ号'))
        } else {
          callback()
        }
      };
      return {
        dataForm: {
          phone: '',
          userName: '',
          userType: '',
          password: '',
          comfirmPassword: '',
          uuid: '',
          captcha: '',
          QQ: '',
        },
        dataRule: {
          userName: [
            {validator: validateuserName, trigger: 'blur'},
          ],
          userType:[
            {required: true, message: '用户角色不能为空', trigger: 'blur'}
          ],
          phone: [
            {validator: validatePhone, trigger: 'blur'}
          ],
          password: [
            {validator: validatePassword, trigger: 'blur'}
          ],
          comfirmPassword: [
            {validator: validateComfirmPassword, trigger: 'blur'}
          ],
          captcha: [
            {required: true, message: '验证码不能为空', trigger: 'blur'}
          ],
          QQ: [
            {validator: validateQQ, trigger: 'blur'}
          ],
        }
      }
    },
    created() {

    },
    methods: {
      // 发送验证码
      getCaptcha() {

      },
      // 注册
      registered() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl('/sys/register'),
              method: 'post',
              data: this.$http.adornData({
                'username': this.dataForm.userName,
                'userType': this.dataForm.userType,
                'salt': this.dataForm.salt,
                'password': this.dataForm.password,
                'qq': this.dataForm.QQ,
                'mobile': this.dataForm.phone,
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message.success('注册成功')
                this.$cookie.set('token', data.token)
                this.$router.replace({name: 'login'})
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      },
      //返回登录页
      Cancel() {
        this.$router.push('/login')
      }
    }
  }
</script>

<style lang="scss">
  .site-wrapper.site-page--login {
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    background-color: rgba(38, 50, 56, .6);
    overflow: hidden;

    &:before {
      position: fixed;
      top: 0;
      left: 0;
      z-index: -1;
      width: 100%;
      height: 100%;
      content: "";
      background-image: url(~@/assets/img/login_bg.jpg);
      background-size: cover;
    }

    .site-content__wrapper {
      position: absolute;
      top: 0;
      right: 0;
      bottom: 0;
      left: 0;
      padding: 0;
      margin: 0;
      overflow-x: hidden;
      overflow-y: auto;
      background-color: transparent;
    }

    .site-content {
      min-height: 100%;
      padding: 30px 500px 30px 30px;
    }

    .brand-info {
      margin: 220px 100px 0 90px;
      color: #fff;
    }

    .brand-info__text {
      margin: 0 0 22px 0;
      font-size: 48px;
      font-weight: 400;
      text-transform: uppercase;
    }

    .brand-info__intro {
      margin: 10px 0;
      font-size: 16px;
      line-height: 1.58;
      opacity: .6;
    }

    .login-main {
      position: absolute;
      top: 0;
      right: 0;
      padding: 50px 60px 100px;
      width: 470px;
      min-height: 100%;
      background-color: #fff;
    }

    .login-title {
      font-size: 16px;
    }

    .login-captcha {
      overflow: hidden;

      > img {
        width: 100%;
        cursor: pointer;
      }
    }

    .login-btn-submit {
      width: 100%;
      margin-top: 38px;
    }
  }
</style>
