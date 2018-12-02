<template>
    <card-wrapper>
        <template slot="title">
          <div>
            <h3 class="headline mb-0">Sample Statistics</h3>
          </div>
        </template>
        <template slot="content">
          <v-data-table
            :headers="headers"
            :items="presented"
            :loading="loading"
            hide-actions
            class="sources">
          <v-progress-linear slot="progress" color="blue" indeterminate></v-progress-linear>
          <template class="source" slot="items" slot-scope="props">
            <td class="text-xs-center">{{ props.item.metricName }}</td>
            <td class="text-xs-center">{{ props.item.populationPrevalence }}</td>
            <td class="text-xs-center">{{ props.item.stratification.males }}</td>
            <td class="text-xs-center">{{ props.item.stratification.females }}</td>
          </template>
          </v-data-table>
        </template>
      </card-wrapper>
</template>

<script>
import { mapActions, mapState } from "vuex";
import CardWrapper from "@/components/CardWrapper.vue";

export default {
  data() {
    return {
      loading: true,
      headers: [
        {
          text: "Criteria",
          value: "criteria",
          align: "center",
          sortable: false
        },
        {
          text: "Population Prevalence",
          value: "population_prevalence",
          align: "center",
          sortable: false
        },
        {
          text: "Stratified by Male",
          value: "stratified_by_male",
          align: "center",
          sortable: false
        },
        {
          text: "Stratified by Female",
          value: "stratified_by_female",
          align: "center",
          sortable: false
        }
      ]
    };
  },
  mounted() {
    this.fetchSampleStatistics().finally(() => {
      this.loading = false;
    });
  },
  methods: {
    ...mapActions(["fetchSampleStatistics"])
  },
  computed: {
    ...mapState(["sampleStatistics"]),
  },
  watch: {
  },
  components: {
    "card-wrapper": CardWrapper
  }
};
</script>

<style lang="scss" scoped>
table.sources,
table.totals {
  width: 100%;
  .headers {
    th {
      &:first-child {
        padding-left: 10px;
        text-align: start;
        width: 39%;
      }
    }
  }
  tr {
    width: 100%;
    td {
      &:first-child {
        padding-left: 10px;
        text-align: start;
        width: 39%;
      }
      width: 22%;
    }
  }
}
</style>
