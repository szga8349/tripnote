import wepy from 'wepy'

export default class testMixin extends wepy.mixin {
  data = {
    mixin: 'This is mixin data.'
  }
  methods = {
    tap () {
      this.mixin = 'mixin data was changed'
      console.log('mixin method tap')
    },

    isSelected(productListSelected, itemId){
      return productListSelected.indexOf(itemId)
    }
  }
  
  // 类型判断
  formatType(type){
    if(type == 1){
      return '限时特价'
    }else if(type == 2){
      return '拼团'
    }else if(type == 3){
      return '直购'
    }
  }

  // 状态判断
  formatStatus(status){
    if(status == 0){
      return '未发布'
    }else if(status == 1){
      return '已发布'
    }else{
      return '已结束'
    }
  }
  
  methods = {
    handleInputChange(e){
      this[e.target.dataset.type] = e.detail.value
    },
  }
  
  onShow() {
    console.log('mixin onShow')
  }

  onLoad() {
    console.log('mixin onLoad')
  }
}
