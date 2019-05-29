// pages/favorite/favorite.js
Page({
  /**
   * 页面的初始数据
   */
  data: {
    searchinput: '',
    dots: true,
    autoplay: true,
    dtime: 3000,
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
  onTapExhibit: function (e) {
    var sid = e.currentTarget.dataset.sid;
    console.log("Open page exhibition_" + sid);
    wx.navigateTo({
      url: '../exhibition/exhibition?sid=' + sid
    })
  },

  input_words: function (e) {
    this.setData({
      searchinput: e.detail.value
    })
  },

  input_confirm: function () {
    console.log("Open page exhibit_" + this.data.searchinput)
    wx.navigateTo({
      url: '../exhibit/exhibit?eid=' + this.data.searchinput
    })
  },

  input_clear: function () {
    this.setData({
      searchinput: '',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log('Opening Index Page')
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