// pages/favorite/favorite.js
const app = getApp()

Page({
  /**
   * 页面的初始数据
   */
  data: {
    msgList: [],
    type: '展览',
    searchinput: '',
    dots: true,
    autoplay: true,
    dtime: 3000,
    wordsList: ['展览', '展品', '展馆'],
    engList: ['show', 'collection', 'exhibition'],
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

  changeType: function () {
    var favoriteThis = this
    wx.showActionSheet({
      itemList: ['展览', '展品', '展馆'],
      success: function (res) {
        var num = res.tapIndex
        if (!res.cancel) {
          console.log(res.tapIndex)//这里是点击了那个按钮的下标
          favoriteThis.setData({
            type: favoriteThis.data.wordsList[res.tapIndex]
          })
          wx.request({
            url: app.globalData.host + '/star_list/object_type/' + favoriteThis.data.engList[res.tapIndex],
            method: 'GET',
            header: {
              'X-Token': wx.getStorageSync('X-Token'),
            },
            success(ans) {
              favoriteThis.setData({
                msgList: ans.data.data.item_list
              })
            }
          })
          // wx.request({
          //   url: app.globalData.host + '/show/' + showThis.data.show.sid + '/appointment',
          //   header: {
          //     'X-Token': wx.getStorageSync('X-Token')
          //   },
          //   method: 'POST',
          //   data: {
          //     arrival_time: res.tapIndex
          //   },
          //   success(res) {
          //     showThis.setData({
          //       exhibition_order_type: "default",
          //       exhibition_order_status: "已预约"
          //     })
          //   },
          //   fail() {
          //   }
          // })
        }
      }
    })
  },

  onTapExhibition: function (e) {
    var sid = e.currentTarget.dataset.sid;
    console.log("Open page exhibition_" + sid);
    wx.navigateTo({
      url: '../exhibition/exhibition?sid=' + sid
    })
  },

  onTapExhibit: function (e) {
    var cid = e.currentTarget.dataset.sid;
    console.log("Open page exhibit_" + cid);
    wx.navigateTo({
      url: '../exhibit/exhibit?cid=' + cid
    })
  },

  onTapMuseum: function (e) {
    var eid = e.currentTarget.dataset.sid;
    console.log("Open page museum_" + eid);
    wx.navigateTo({
      url: '../museum/museum?eid=' + eid
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
    var favoriteThis = this
    console.log('Opening Favorite Page')
    wx.request({
      url: app.globalData.host + '/star_list/object_type/' + favoriteThis.data.engList[0],
      method: 'GET',
      header: {
        'X-Token': wx.getStorageSync('X-Token'),
      },
      success(ans) {
        favoriteThis.setData({
          msgList: ans.data.data.item_list
        })
      }
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