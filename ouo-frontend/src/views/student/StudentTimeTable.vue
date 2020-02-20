<template>
  <div class="timetable-wrap">
    <div class="container">
      <time-table ref="timeTable"></time-table>
    </div>
  </div>
</template>

<script>
import * as api from "../../api/student/timetable";
import TimeTable from "../../components/TimeTable";

export default {
  name: "StudentTimeTable",
  components: { TimeTable },
  created() {
    api.get().then(res => {
      this.$refs.timeTable.updateData(res);
    });
  }
};
</script>

<style scoped></style>
