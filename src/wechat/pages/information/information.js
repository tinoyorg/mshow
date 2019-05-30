// pages/information/information.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    searchinput: '',
    dots: true,
    autoplay: true,
    dtime: 3000,
    swipers: ['index-swiper1.png', 'index-swiper2.png', 'index-swiper3.png'],
  },
  /**
   * 自定义函数
   */

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    console.log('Opening Information Page')
    var inforThis = this
    wx.request({
      url: app.globalData.host + '/user/' + wx.getStorageSync('uid'),
      header: {
        'X-Token': wx.getStorageSync('X-Token')
      },
      method: 'GET',
      success(res) {
        inforThis.setData({
          userInfo: res.data.data.user_info
        })
        console.log(res)
      },
      fail() {
        console.log('Getting Information Page Fail')
      }
    })
  },

  getUserInfo: function (e) {
    var getUserInfoThis = this
    wx.login({
      success: function (log) {
        if (log.code) {
          console.log(log.code)
          console.log(e.detail.iv)
          wx.request({
            url: app.globalData.url + '/user/login',
            data: {
              code: log.code,
              encryptedData: e.detail.encryptedData,
              iv: e.detail.iv
            },
            method: 'POST',
            success(res) {
              app.globalData.userInfo = e.detail.userInfo
              getUserInfoThis.setData({
                userInfo: e.detail.userInfo,
                hasUserInfo: true
              })
            },
            fail(res) {
              console.log('request fail')
            }
          })
        } else { }
      },
      fail: function (res) { },
      complete: function (res) { },
    })
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