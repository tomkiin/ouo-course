<template>
    <div class="head-bar">
        <div class="header-ico">
            <i class="el-icon-s-home"></i>
        </div>
        <div class="logo">OUO课程管理系统</div>
        <div class="head-right">
            <div class="head-user-con">


                <el-dropdown @command="handleCommand" class="user-name" trigger="click">
          <span class="el-dropdown-link">
            {{ username }}
            <i class="el-icon-caret-bottom"></i>
          </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="logout">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
    </div>
</template>
<script>
    import {logout} from "../api/user";

    export default {
        name: "Header",
        data() {
            return {
                fullscreen: false
            };
        },
        computed: {
            username() {
                return this.$store.state.status.username;
            }
        },
        methods: {
            handleCommand(command) {
                if (command === "logout") {
                    logout().then(() => {
                        this.$store.commit("logout");
                        this.$message.success("注销成功");
                        this.$router.push("/login");
                    });
                }
            },
            handleFullScreen() {
                let element = document.documentElement;
                if (this.fullscreen) {
                    if (document.exitFullscreen) {
                        document.exitFullscreen();
                    } else if (document.webkitCancelFullScreen) {
                        document.webkitCancelFullScreen();
                    } else if (document.mozCancelFullScreen) {
                        document.mozCancelFullScreen();
                    } else if (document.msExitFullscreen) {
                        document.msExitFullscreen();
                    }
                } else {
                    if (element.requestFullscreen) {
                        element.requestFullscreen();
                    } else if (element.webkitRequestFullScreen) {
                        element.webkitRequestFullScreen();
                    } else if (element.mozRequestFullScreen) {
                        element.mozRequestFullScreen();
                    } else if (element.msRequestFullscreen) {
                        // IE11
                        element.msRequestFullscreen();
                    }
                }
                this.fullscreen = !this.fullscreen;
            }
        }
    };
</script>
<style scoped>
    .head-bar {
        position: relative;
        box-sizing: border-box;
        height: 70px;
        font-size: 22px;
        color: #fff;
        background-color: white;
        border-radius: 15px;
        margin: 20px;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .header-ico {
        float: left;
        padding: 0 21px;
        line-height: 70px;
        color: #505458;
    }

    .head-bar .logo {
        float: left;
        width: 250px;
        line-height: 70px;
        color: #505458;
        font-weight: bold;
    }

    .head-right {
        float: right;
        padding-right: 50px;
    }

    .head-user-con {
        display: flex;
        height: 70px;
        align-items: center;
    }

    .user-name {
        font-size: 15px;
    }

    .el-dropdown-link {
        color: #505458;
        cursor: pointer;
        font-weight: bold;
        font-size: 20px;
    }

</style>
