<script>
  export default{
    name: 'NoData',
    props: {
      noDataVisible:{
        type: Boolean,
        default: true,
      }
    },
    data: function(){
      return {
        imgSrc: require('../../../assets/images/table_nodata.png')
      }
    },
    computed: {
      visible(){
        return this.noDataVisible;
      }
    },
    render(h){
      let {visible} = this;
      this.$slots.default = <div>{this.$t('content.common.zwsj')}</div>;
      return  <div class="nodata-container" v-show={visible}>
                <img src={this.imgSrc} />
                {this.$slots.default}
              </div>
    }
  }
</script>
<style scoped>
  .nodata-container{
    text-align: center;
    padding: 40px 0;
  }
  .nodata-container img{
    margin: 0 auto;
  }
  .nodata-container div{
    margin-top: 10px;
    color: #bfbfc2;
    font-size: 16px;
  }
  .nodata-container div .emphasize{
    color:#f00;
  }
</style>
