<template>
  <div class="index">
    <div class="leftBox">
      <div class="personal">
        <h4>
          <i class="el-icon-postcard"></i>
          <span>个人中心</span>
        </h4>
        <div class="userInformation">
          <div class="avatar">
            <avatar
              :size="100"
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
            ></avatar>
            <span class="ordinary">普通会员</span>
            <p class="promote">提升会员等级?</p>
          </div>
          <div class="content">
            <p>
              账户余额:{{balance}}元&nbsp;&nbsp;&nbsp;
              <span>充值</span>
            </p>
            <p>累计充值:{{cumulative}}元</p>
            <p>我的积分:{{integral}}积分</p>
            <p>
              合伙人福利&nbsp;&nbsp;&nbsp;
              <span>福利</span>
            </p>
          </div>
        </div>
      </div>
      <div class="product">
        <h4>
          <i class="iconfont icon-xiaoshoubaogao"></i>
          <span>品宣发布</span>
        </h4>
        <div class="productMain">
          <span class="bigBtn">资源网站</span>
          <span class="bigBtn">自媒体</span>
          <span class="bigBtn">企业问答</span>
          <span class="smallBtn">贴吧</span>
          <span class="smallBtn">论坛</span>
          <span class="smallBtn">友链</span>
          <span class="smallBtn">代写</span>
          <span class="smallBtn">百度百科</span>
        </div>
      </div>
      <div class="quotations">
        <h4>
          <i class="el-icon-s-management"></i>
          <span>小红书</span>
        </h4>
        <div class="quotationsMain">
          <span v-for="(item,index) in quotationsList" :key="index">{{item}}</span>
        </div>
      </div>
    </div>
    <div class="centerBox">
      <div class="profit">
        <h4>
          <i class="iconfont icon-money-cny-circle-fill"></i>
          <span>收益中心</span>
        </h4>
        <div class="profitMain">
          <p>
            总收益:{{revenue}}元
            <span>余额转入</span>
          </p>
          <p>
            可提现:{{withdrawal}}元
            <span>提现申请</span>
          </p>
          <p>
            提现审核中:{{examine}}元
            <span>接单提醒</span>
          </p>
        </div>
      </div>
      <div class="profit">
        <h4>
          <i class="el-icon-pie-chart"></i>
          <span>我的资源</span>
        </h4>
        <div class="profitMain">
          <p>
            已上架资源:{{resourcesOn}}个
            <span>新增资源</span>
          </p>
          <p>
            未上架资源:{{resourcesOff}}个
            <span>资源列表</span>
          </p>
          <p>
            保量资源:{{resources}}个
            <span>入驻</span>
          </p>
        </div>
      </div>
      <div class="receiving">
        <h4>
          <i class="el-icon-document"></i>
          <span>我的资源</span>
        </h4>
        <div class="receivingMain">
          <p>
            待接单
            <span>{{waitRec}}</span>单
          </p>
          <p>
            已接单
            <span>{{isRec}}</span>篇
          </p>
          <p>
            已发布
            <span>{{isRel}}</span>单
          </p>
          <p>
            问题稿件
            <span>{{waitRec}}</span>篇
          </p>
        </div>
      </div>
    </div>
    <div class="rightBox">
      <h4>
        <i class="el-icon-message-solid"></i>
        <span>我的资源</span>
        <a href="#">行业动态</a>
      </h4>
      <ul>
        <li :class="{changeBgcColor:choiceItNum==1}" @click="choiceIt(1)">全部</li>
        <li :class="{changeBgcColor:choiceItNum==2}" @click="choiceIt(2)">系统消息</li>
        <li :class="{changeBgcColor:choiceItNum==3}" @click="choiceIt(3)">资源消息</li>
        <li :class="{changeBgcColor:choiceItNum==4}" @click="choiceIt(4)">稿件消息</li>
      </ul>
      <div class="listMain" v-show="choiceItNum==1">
        <p v-for="(item,index) in allList1" :key="index">{{item}}</p>
      </div>
      <div class="listMain" v-show="choiceItNum==2">
        <p v-for="(item,index) in allList2" :key="index">{{item}}</p>
      </div>
      <div class="listMain" v-show="choiceItNum==3">
        <p v-for="(item,index) in allList3" :key="index">{{item}}</p>
      </div>
      <div class="listMain" v-show="choiceItNum==4">
        <p v-for="(item,index) in allList4" :key="index">{{item}}</p>
      </div>
    </div>
  </div>
</template>

<script>
  import "../../../public/static/js/rem"
  import Avatar from 'vue-avatar'

  export default {
    data() {
      return {
        allList1: [
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
        ],
        allList2: [
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
        ],
        allList3: [
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
        ],
        allList4: [
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
          "2020-07-16:云南经济网（包新闻源）今日降价（联盟）！",
        ],
        choiceItNum: 1,
        tabPosition: "left",
        /**
         * @balance 账户余额
         * @cumulative 累计充值
         * @integral 我的积分
         */
        balance: 0,
        cumulative: 0,
        integral: 0,
        quotationsList: [
          "测试内容",
          "测试内容",
          "测试内容",
          "测试内容",
          "测试内容",
          "测试内容",
        ],
        /**
         * @revenue 总收益
         * @withdrawal 可提现
         * @examine 提现审核中
         */
        revenue: 55.0,
        withdrawal: 55.0,
        examine: 0,
        /**
         * @resourcesOn 已上架资源
         * @resourcesOff 未上架资源
         * @resources 保量资源
         */
        resourcesOn: 3,
        resourcesOff: 0,
        resources: 0,
        /**
         * @waitRec 待接单
         * @isRec 已接单
         * @isRel 已发布
         * @waitRec 问题稿件
         */
        waitRec: 0,
        isRec: 0,
        isRel: 0,
        waitRec: 0,
      };
    },
    methods: {
      choiceIt(i) {
        this.choiceItNum = i;
      },
    },
    components: {
      Avatar
    },
  };
</script>

<style scoped>

  .index {
    font-size: 0.16rem;
    width: 15rem;
    display: flex;
    justify-content: space-around;
  }
  .leftBox,
  .centerBox,
  .rightBox {
    width: 5.4rem;
    padding: 0.5rem;
  }
  .el-icon-postcard,
  .el-icon-message-solid,
  .el-icon-document,
  .el-icon-pie-chart,
  .icon-money-cny-circle-fill,
  .icon-xiaoshoubaogao {
    color: orangered;
    font-size: 0.24rem;
  }
  .el-icon-s-management {
    color: #ff2e43;
    font-size: 0.24rem;
  }

  .personal,
  .product,
  .profit{
    height: 3.0rem;
  }
  .personal h4,
  .quotations h4,
  .product h4 {
    display: flex;
    align-items: center;
  }

  .personal h4 span,
  .product h4 span {
    margin-left: 0.2rem;
  }
  .quotations h4 span {
    margin-left: 0.1rem;
  }
  h4 {
    display: flex;
    align-items: center;
    height: 0.2rem;
    position: relative;
  }

  h4 span {
    margin-left: 0.1rem;
  }
  h4 a {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    right: 0;
    width: 0.8rem;
    height: 0.2rem;
    background-color: #ff6100;
    text-decoration: none;
    color: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
    font-weight: 400;
  }

  .userInformation {
    display: flex;
    padding: 0.2rem 0;
  }
  .avatar {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
    position: relative;
    width: 1.4rem;
  }
  .avatar .el-icon-user-solid::after {
    width: 0.28rem;
    height: 0.28rem;
  }
  .avatar .ordinary {
    position: absolute;
    bottom: 0.4rem;
    left: 50%;
    transform: translateX(-50%);
    background-color: #ff6100;
    color: #fff;
    border-radius: 0.05rem;
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 0 0.02rem;
    width: 0.8rem;
  }
  .avatar .promote {
    border: 0.01rem solid #ff6100;
    border-radius: 0.05rem;
    color: #ff6100;
    margin-top: 0.2rem;
  }
  .content {
    width: 2rem;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    margin-left: 0.6rem;
  }
  .content span {
    display: inline-block;
    background-color: #ff6100;
    color: #fff;
    border-radius: 0.05rem;
    padding: 0 0.05rem;
  }
  /* .product {
    margin-top: 0.8rem;
  } */
  .productMain {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    align-content: space-around;
    margin-top: 0.2rem;
    height: 1.4rem;
  }
  .bigBtn,
  .smallBtn {
    height: 0.6rem;
    width: 1.28rem;
    border: 0.01rem solid #ff6100;
    border-radius: 0.05rem;
    color: #ff6100;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .smallBtn {
    height: 0.2rem;
    margin: 0 0.2rem;
    width: 0.92rem;
  }
  .quotations {
    margin-top: 0.2rem;
  }
  .quotationsMain {
    margin-top: 0.2rem;
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    align-content: space-around;
  }
  .quotationsMain span {
    height: 0.6rem;
    width: 1.6rem;
    margin: 0.1rem;
    border: 0.01rem solid #ff6100;
    border-radius: 0.05rem;
    display: flex;
    justify-content: center;
    align-items: center;
    color: #ff6100;
  }
  .quotationsMain span:nth-child(1) {
    height: 0.4rem;
    width: 1.2rem;
  }
  .quotationsMain span:nth-child(2) {
    height: 0.4rem;
    width: 1.2rem;
  }
  .profitMain {
    padding: 0.2rem;
  }
  .profitMain p {
    padding: 0.1rem 0.2rem;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  .profitMain p span {
    width: 0.9rem;
    border: 0.01rem solid #ff6100;
    border-radius: 0.05rem;
    color: #ff6100;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .receivingMain {
    padding: 0.2rem;
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    align-content: center;
  }
  .receivingMain p {
    padding: 0.1rem 0.2rem;
    width: 50%;
  }
  .receivingMain p span {
    color: #ff6100;
    font-weight: 600;
  }
  .rightBox ul {
    display: flex;
    margin-top: 0.3rem;
  }
  .rightBox ul li {
    flex: 1;
    display: flex;
    justify-content: center;
    align-items: center;
    border: 0.01rem solid #ff6100;
    border-right: 0;
    cursor: pointer;
  }
  .rightBox ul li:last-child {
    border-right: 1px solid #ff6100;
    border-radius: 0 0.05rem 0.05rem 0;
  }
  .rightBox ul li:first-child {
    border-radius: 0.05rem 0 0 0.05rem;
  }
  .changeBgcColor {
    background-color: #ff6100;
    color: #fff;
  }
  .listMain {
    padding: 0.1rem 0.2rem;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }
  .listMain p {
    font-size: 0.14rem;
    margin-top: 0.2rem;
  }
</style>
