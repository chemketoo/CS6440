import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    county: {
      county: 'Cameron County',
      population: 423725
    },
    sources: [],
    metrics: []
  },
  getters: {
    selectedCountyDetails: state => {
      return state.county
    }
  },
  mutations: {
    setSources (state, sources) {
      state.sources = sources
    },
    setMetrics (state, metrics) {
      state.metrics = metrics
    }
  },
  actions: {
    fetchSources: ({ commit }) => {
      return fetch(`${process.env.VUE_APP_BULK_FHIR_SERVER_HOST}/api/sources`)
        .then((response) => response.json())
        .then((json) => {
          commit('setSources', json.data)
        })
    },
    fetchSampleStatistics: ({ commit }) => {
      return fetch(`${process.env.VUE_APP_BULK_FHIR_SERVER_HOST}/api/metrics  `)
        .then((response) => response.json())
        .then((json) => {
          commit('setMetrics', json.data)
        })
    }
  }
})
