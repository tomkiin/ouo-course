<template>
  <div class="sidebar">
    <el-menu
      :default-active="routePath"
      active-text-color="#20a0ff"
      background-color="white"
      class="sidebar-el-menu"
      router
      text-color="#505458"
    >
      <template v-for="item in items">
        <template v-if="item.subs">
          <el-submenu :index="item.index" :key="item.index">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <el-submenu
                :index="subItem.index"
                :key="subItem.index"
                v-if="subItem.subs"
              >
                <template slot="title">{{ subItem.title }}</template>
                <el-menu-item
                  :index="threeItem.index"
                  :key="i"
                  v-for="(threeItem, i) in subItem.subs"
                  >{{ threeItem.title }}
                </el-menu-item>
              </el-submenu>
              <el-menu-item :index="subItem.index" :key="subItem.index" v-else
                >{{ subItem.title }}
              </el-menu-item>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.index" :key="item.index">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "SideBar",
  computed: {
    items() {
      return this.$store.state.sideBarItems;
    },
    routePath() {
      return this.$route.path;
    }
  }
};
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 100px;
  bottom: 20px;
  overflow-y: scroll;
  border-radius: 15px;
  margin: 20px;
  font-weight: bold;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}

.sidebar::-webkit-scrollbar {
  width: 0;
}

.sidebar-el-menu:not(.el-menu--collapse) {
  width: 200px;
}

.sidebar > ul {
  height: 100%;
}
</style>
