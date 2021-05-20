import axios from 'axios'

export default {
  data () {
    return {
      info: null,
      running: true
    }
  },
  methods: {
    startStop: function () {
      // éteindre
      if (!this.running) {
        axios
          .get('http://192.168.1.107:8080/stop')
          .then(response => (this.info = response))
      } else { // allumer
        axios
          .get('http://192.168.1.107:8080/start')
          .then(response => (this.info = response))
      }
      this.running = !this.running
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
    }
  }
}
