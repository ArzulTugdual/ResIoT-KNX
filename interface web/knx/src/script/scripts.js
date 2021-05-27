import axios from 'axios'

export default {
  data () {
    return {
      info: null
    }
  },
  methods: {
    startStop: function (running) {
      // éteindre
      if (running) {
        axios
          .get('http://192.168.1.107:8080/stop')
          .then(response => (this.info = response))
      } else { // allumer
        axios
          .get('http://192.168.1.107:8080/start')
          .then(response => (this.info = response))
      }
    },
    accelerer: function () {
      axios
        .get('http://192.168.1.107:8080/speedup')
        .then(response => (this.info = response))
    },
    ralentir: function () {
      axios
        .get('http://192.168.1.107:8080/speeddown')
        .then(response => (this.info = response))
    },
    aleatoire: function () {
      axios
        .get('http://192.168.1.107:8080/random')
        .then(response => (this.info = response))
    },
    phare: function () {
      axios
        .get('http://192.168.1.107:8080/phare')
        .then(response => (this.info = response))
    }
  }
}
