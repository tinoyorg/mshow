// pages/collections/collections.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    collection_list: [],
    dots: true,
    autoplay: true,
    dtime: 3000,
    swipers: ['index-swiper1.png', 'index-swiper2.png', 'index-swiper3.png'],
    exhibit_box: [
      {
        sid: 1,
        image: 'index-show1.png',
        name: '展品一',
        address: '展馆名称',
        time: '还剩3天',
        money: '998',
        star: 123,
        skim: 3124,
        ticket: 2343
      },
      {
        sid: 2,
        image: 'index-show2.png',
        name: '展品二',
        address: '展馆名称',
        time: '还剩3天',
        money: '599',
        star: 112323,
        skim: 123,
        ticket: 2343543
      },
      {
        sid: 3,
        image: 'index-show3.png',
        name: '展品三',
        address: '展馆名称',
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
    var cid = e.currentTarget.dataset.cid;
    console.log("Open page exhibition_" + cid);
    wx.navigateTo({
      url: '../exhibit/exhibit?cid=' + cid
    })
  },

  input_confirm: function (e) {
    var value = e.detail.value;
    console.log("Open page exhibit_" + value)
    wx.navigateTo({
      url: '../exhibit/exhibit?eid=' + value
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    console.log('Opening Collections Page')
    var collections = JSON.parse(e.collectionsBean)
    this.setData({
      collection_list: collections
    })
    console.log(this.data.collection_list)
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