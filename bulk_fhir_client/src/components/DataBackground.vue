<template>
<div>
  <v-layout align-center justify-center row fill-height wrap>
    <v-flex xs12 xl8>
      <background-card></background-card>
    </v-flex>
  </v-layout>

  <v-layout align-center justify-center row fill-height wrap>
    <v-flex xs12 xl8>
      <county-background-card></county-background-card>
    </v-flex>
  </v-layout>

  <v-layout align-center justify-center row fill-height wrap>
    <v-flex xs12 xl8>
      <sample-statistics-card></sample-statistics-card>
    </v-flex>
  </v-layout>
  </div>
</template>
<script>
import BackgroundCard from "@/components/BackgroundCard.vue";
import CountyBackgroundCard from "@/components/CountyBackgroundCard.vue";
import SampleStatisticsCard from '@/components/SampleStatisticsCard.vue';

export default {
  components: {
    "background-card": BackgroundCard,
    "county-background-card": CountyBackgroundCard,
    "sample-statistics-card": SampleStatisticsCard
  }
};
</script>
