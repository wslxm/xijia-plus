<template>
    <div style="font-size: 14px;margin-left: 1%; width: 98%;">

        <el-row>
            <el-col :span="12">
                <el-card style="height: 220px; margin-top: 0px">
                    CPU
                    <el-divider></el-divider>
                    <div class="grid-content bg-purple">
                        <el-col :span="16">
                            <div> 属性</div>
                            <div style="margin-top: 5px"> 核心数</div>
                            <div style="margin-top: 5px"> 用户使用率</div>
                            <div style="margin-top: 5px"> 系统使用率</div>
                            <div style="margin-top: 5px"> cpu总使用率</div>
                        </el-col>
                        <el-col :span="8">
                            <div> 值</div>
                            <div style="margin-top: 5px">{{this.obj.cpu.numberOfCores}}</div>
                            <div style="margin-top: 5px">{{this.obj.cpu.userUtilization}} %</div>
                            <div style="margin-top: 5px">{{this.obj.cpu.systemUtilization}} %</div>
                            <div style="margin-top: 5px">{{this.obj.cpu.usageRate}} %</div>
                        </el-col>
                    </div>
                </el-card>
            </el-col>
           <!-- <el-col :span="2"></el-col>-->
            <el-col :span="12">
                <el-card style="height: 220px;margin-top: 0px;">
                    内存
                    <el-divider></el-divider>
                    <div class="grid-content bg-purple-light">
                        <el-col :span="10">
                            <div> 属性</div>
                            <div style="margin-top: 5px"> 总内存</div>
                            <div style="margin-top: 5px"> 已用内存</div>
                            <div style="margin-top: 5px"> 剩余内存</div>
                            <div style="margin-top: 5px"> 使用率</div>
                        </el-col>
                        <el-col :span="10">
                            <div> 内存</div>
                            <div style="margin-top: 5px"> {{this.obj.ram.totalMemory}} GB</div>
                            <div style="margin-top: 5px"> {{this.obj.ram.usedMemory}} GB</div>
                            <div style="margin-top: 5px"> {{this.obj.ram.remainingMemory}} GB</div>
                            <div style="margin-top: 5px"> {{this.obj.ram.usageRate}} %</div>
                        </el-col>
                        <el-col :span="4">
                            <div> JVM</div>
                            <div style="margin-top: 5px"> {{this.obj.jvmRam.totalMemory}} MB</div>
                            <div style="margin-top: 5px"> {{this.obj.jvmRam.usedMemory}} MB</div>
                            <div style="margin-top: 5px"> {{this.obj.jvmRam.remainingMemory}} MB</div>
                            <div style="margin-top: 5px"> {{this.obj.jvmRam.usageRate}} %</div>
                        </el-col>
                    </div>
                </el-card>
            </el-col>
        </el-row>


        <el-card style="height: 150px;margin-top: 10px">
            <el-row>
                <el-col :span="24">
                    服务器信息
                    <el-divider></el-divider>
                    <div class="grid-content bg-purple">
                        <el-col :span="6">
                            <div>服务器名称</div>

                            <div style="margin-top: 10px">{{this.obj.serverInformation.name}}</div>
                        </el-col>
                        <el-col :span="6">
                            <div>服务器IP</div>
                            <div style="margin-top: 10px">{{this.obj.serverInformation.ip}}</div>
                        </el-col>
                        <el-col :span="6">
                            <div>操作系统</div>
                            <div style="margin-top: 10px">{{this.obj.serverInformation.operatingSystem}}</div>
                        </el-col>
                        <el-col :span="6">
                            <div>系统架构</div>
                            <div style="margin-top: 10px">{{this.obj.serverInformation.systemStructure}}</div>
                        </el-col>
                    </div>
                </el-col>
                <el-col :span="4"></el-col>
            </el-row>
        </el-card>

        <el-card style="height: 210px;margin-top: 10px">
            <el-row>
                <el-col :span="24">
                    JWM 虚拟机信息
                    <el-divider></el-divider>
                    <div class="grid-content bg-purple">
                        <el-col :span="4">
                            <div>java名称</div>
                            <div style="margin-top: 5px">java版本</div>
                            <div style="margin-top: 5px">JDK路径</div>
                            <div style="margin-top: 5px">项目路径</div>
                            <div style="margin-top: 5px">程序PID</div>
                        </el-col>
                        <el-col :span="8">
                            <div>{{this.obj.jvmInformation.javaName}}</div>
                            <div style="margin-top: 5px">{{this.obj.jvmInformation.javaVersion}}</div>
                            <div style="margin-top: 5px">{{this.obj.jvmInformation.jdkPath}}</div>
                            <div style="margin-top: 5px">{{this.obj.jvmInformation.projectPath}}</div>
                            <div style="margin-top: 5px">{{this.obj.jvmInformation.pid}}</div>
                        </el-col>
                        <el-col :span="6">
                            <div>启用时间</div>
                            <div style="margin-top: 5px">运行时长</div>
                        </el-col>
                        <el-col :span="6">
                            <div>{{this.obj.jvmInformation.startTime}}</div>
                            <div style="margin-top: 5px">{{ dateDifference(this.obj.jvmInformation.startTime, new Date().getTime()) }}</div>
                        </el-col>
                    </div>
                </el-col>
                <el-col :span="4"></el-col>
            </el-row>
        </el-card>

        <el-card style="height: 150px;margin-top: 10px">
            <el-row>
                <el-col :span="24">
                    磁盘状态
                    <el-divider></el-divider>
                    <div class="grid-content bg-purple">
                        <el-col :span="5">
                            <div>盘符路径</div>
                            <div style="margin-top: 10px">/</div>
                        </el-col>
                        <el-col :span="5">
                            <div>总大小</div>
                            <div style="margin-top: 10px">{{this.obj.fileInfo.total}} GB</div>
                        </el-col>
                        <el-col :span="5">
                            <div>可用大小</div>
                            <div style="margin-top: 10px">{{this.obj.fileInfo.free}} GB</div>
                        </el-col>
                        <el-col :span="5">
                            <div>已用大小</div>
                            <div style="margin-top: 10px">{{this.obj.fileInfo.usable}} GB</div>
                        </el-col>
                        <el-col :span="4">
                            <div>已用百分比</div>
                            <div style="margin-top: 10px">{{this.obj.fileInfo.usedRatio}} %</div>
                        </el-col>
                    </div>
                </el-col>
                <el-col :span="4"></el-col>
            </el-row>
        </el-card>
        <div style="height: 50px"></div>
    </div>
</template>

<script>
    export default {
        name: "wel",
        data() {
            return {
                uri: {
                    jvmInfo: "/api/admin/xj/jvm/jvmInfo"
                },
                obj: {
                    fileInfo: {},
                    cpu: {},
                    jvmRam: {},
                    ram: {},
                    serverInformation: {},
                    jvmInformation: {}
                }
            }
        },
        computed: {},
        activated: function () {
            this.crud.get(this.uri.jvmInfo).then((res) => {
                this.obj = res.data.data;
            })
        },
        created() {
            this.crud.get(this.uri.jvmInfo).then((res) => {
                this.obj = res.data.data;
            })
        },
        methods: {
            dateDifference(faultDate, completeTime) {
                var stime = new Date(faultDate).getTime();
                var etime = new Date(completeTime).getTime();
                var t = etime - stime;  //两个时间戳相差的毫秒数
                let d = Math.floor(t / 1000 / 60 / 60 / 24);
                let h = Math.floor(t / 1000 / 60 / 60 % 24);
                let m = Math.floor(t / 1000 / 60 % 60);
                let s = Math.floor(t / 1000 % 60);
                let html = d + "天 " + h + "时 " + m + "分 " + s + "秒";
                return html;
            }
        }
    };
</script>

<style scoped="scoped" lang="scss">

</style>
