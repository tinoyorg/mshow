// pages/exhibit/exhibit.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    collection: {},
    comment_list: [],
    exhibit_introduce: "",
    exhibit_introduce_text: "",
    exhibit_introduce_length: 0,
    exhibit_introduce_full: true,
    image_url: "/image/collection/qhc.jpg",
    exhibit_name: "珍藏版GTX 770",
    like_status: false,
    like_icon: "/image/icon/like.png",
    like_num: 770,
    exhibit_owner: "英伟达",
    exhibit_time: "2013",
    exhibit_from: "美国加利福尼亚州圣克拉拉市",
    exhibit_id: "GeForceGTX770",
    introduce_tip: "展开全文",
    introduce_tip_icon: "/image/icon/more.png",
    exhibit_introduce: "",
    exhibit_introduce_text: "NVIDIA于2013年5月30日正式发布GeForce GTX 770。新一代 GeForce GTX 770 堪称快马加鞭，它是一款完全专为高速、超流畅的游戏体验而设计的高性能显卡。它囊括了 NVIDIA GPU Boost 2.0 等独一无二的游戏技术使其能够高速运行。实现相当快的速度。使用 GTX 770，没有什么可以阻挡你呈现和驾驭最具挑战性的游戏。",
    conmments: [
      {
        avatar: "/image/user-unlogin.png",
        nickname: "我没名字",
        time: "2020-05-29",
        context: "李白真的好帅好帅哦"
      },
      {
        avatar: "/image/user-unlogin.png",
        nickname: "我有名字",
        time: "2020-05-29",
        context: "我真的好帅好帅哦"
      },
      {
        avatar: "/image/user-unlogin.png",
        nickname: "你没名字",
        time: "2020-05-29",
        context: "你真的好帅好帅哦"
      }
    ]
  },
  /**
   * 自定义函数
   */
  onComment: function (e) {
    var collectThis = this
    console.log(e.detail.value.comment)
    if (wx.getStorageSync('X-Token')) {
      wx.request({
        url: app.globalData.host + '/object_type/collection/object_id/' + collectThis.data.collection.cid + '/comment',
        header: {
          'X-Token': wx.getStorageSync('X-Token')
        },
        data: {
          content: e.detail.value.comment
        },
        method: 'POST',
        success(res) {
          console.log(res)
        },
        fail() {
        }
      })
    } else {
      wx.showToast({
        title: '请先登录',
        icon: 'none',
        duration: 2000//持续的时间
      })
    }
  },

  fullText: function () {
    this.exhibit_fullText = !this.exhibit_fullText;
    if (this.exhibit_fullText === true) {
      this.setData({
        exhibit_introduce: this.data.exhibit_introduce_text,
        introduce_tip: "收起全文",
        introduce_tip_icon: "/image/icon/less.png"
      })
    } else {
      this.setData({
        exhibit_introduce: this.data.exhibit_introduce_text.substring(0, 90) + "...",
        introduce_tip: "展开全文",
        introduce_tip_icon: "/image/icon/more.png"
      })
    }
  },

  changeLike: function () {
    this.like_status = !this.like_status;
    if (this.like_status === true) {
      this.setData({
        like_icon: "/image/icon/like-h.png"
      })
    } else {
      this.setData({
        like_icon: "/image/icon/like.png"
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (e) {
    console.log('Opening Exhibit Page')
    var exhibitThis = this
    wx.request({
      url: app.globalData.host + '/collection/' + e.cid + '/collection_content',
      method: 'GET',
      success(res) {
        console.log(res)
        exhibitThis.setData({
          collection: res.data.data.collection,
          comment_list: res.data.data.comment_list,
          exhibit_introduce: res.data.data.collection.introduce,
          exhibit_introduce_text: res.data.data.collection.introduce,
          exhibit_introduce_length: res.data.data.collection.introduce.length
        })
        if (exhibitThis.data.show_introduce_length > 90) {
          exhibitThis.setData({
            exhibit_introduce: exhibitThis.data.exhibit_introduce_text.substring(0, 90) + "...",
            exhibit_introduce_full: false
          })
        }
        console.log(exhibitThis.data.collection)
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.setData({
      exhibit_introduce: this.data.exhibit_introduce_text.substring(0, 90) + "...",
      exhibit_order: false,
      exhibit_order_type: "primary",
      exhibit_order_status: "预约"
    });
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