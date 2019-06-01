// pages/museums/museums.js
const app = getApp()

Page({
  /**
   * 页面的初始数据
   */
  data: {
    searchinput: '',
    dots: true,
    autoplay: true,
    dtime: 3000,
    exhibition_list: [],
    swipers: ['index-swiper1.png', 'index-swiper2.png', 'index-swiper3.png'],
    exhibit_box: [
      {
        sid: 1,
        image: 'index-show1.png',
        name: '展览一',
        address: '展馆地址展馆地址',
        time: '还剩3天',
        money: '998',
        star: 123,
        skim: 3124,
        ticket: 2343
      },
      {
        sid: 2,
        image: 'index-show2.png',
        name: '展览二',
        address: '展馆地址展馆地址',
        time: '还剩3天',
        money: '599',
        star: 112323,
        skim: 123,
        ticket: 2343543
      },
      {
        sid: 3,
        image: 'index-show3.png',
        name: '展览三',
        address: '展馆地址展馆地址',
        time: '还剩3天',
        money: '399',
        star: 2,
        skim: 23,
        ticket: 533
      }
    ]
  },
  /**
   * 自定义函数
   */
  onTapMuseum: function (e) {
    var eid = e.currentTarget.dataset.sid;
    console.log("Open page museum_" + eid);
    wx.navigateTo({
      url: '../museum/museum?eid=' + eid
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var indexThis = this
    wx.request({
      url: app.globalData.host + '/exhibition/exhibition_list',
      method: 'GET',
      success(res) {
        indexThis.setData({
          exhibition_list: res.data.data.exhibition_list
        })
      },
      fail() {
        console.log('Request Fail')
      }
    })
    console.log('Opening Museums Page')
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})