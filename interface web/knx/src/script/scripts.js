import axios from 'axios'

export default {
  data () {
    return {
      info: null
    }
  },
  methods: {
    allumer: function () {
      axios
        .get('https://api.coindesk.com/v1/bpi/currentprice.json')
        .then(response => (this.info = response))
    }
  }
}
