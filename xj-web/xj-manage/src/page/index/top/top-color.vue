<template>
  <el-color-picker size="mini"
                   class="theme-picker"
                   popper-class="theme-picker-dropdown"
                   v-model="themeVal"></el-color-picker>
</template>

<script>
import color from "@/util/color";
export default {
  name: "topColor",
  mixins: [color()],
  data () {
    return {
      chalk: ""
    };
  }
};
</script>

<style>
.theme-picker .el-color-picker__trigger {
  vertical-align: middle;
}

.theme-picker-dropdown .el-color-dropdown__link-btn {
  display: none;
}
</style>
