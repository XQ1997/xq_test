<template>
    <div id="edit">
        <el-button plain @click="back">返回列表</el-button>
        <el-form style="margin-top:30px" ref="form" :model="movie" label-width="80px" size="mini">
            <el-input v-model="movie.id" type="hidden"></el-input>
            <el-form-item label="电影名称:">
                <el-input v-model="movie.title"></el-input>
            </el-form-item>
             <el-form-item label="评分:">
                <el-input v-model="movie.rate"></el-input>
            </el-form-item>
            <el-form-item label="导演:">
                <el-input v-model="movie.director"></el-input>
            </el-form-item>
            <el-form-item label="发行时间:"> 
                <el-date-picker v-model="movie.releaseYear" 
                        type="date"
                        placeholder="选择日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="上映时间:">
                <el-date-picker v-model="movie.sendTime" 
                        type="date"
                        placeholder="选择日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item label="简介:" prop="desc">
                <el-input type="textarea" v-model="movie.description"></el-input>
            </el-form-item>
            <el-form-item size="large">
                <el-button type="primary" @click="onSubmit">立即修改</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
import api from '../const/url';

export default{
    name : 'edit',
    data(){
        return{
            movie:{}
        }
    },
    methods :{
        back:function() {
            this.$router.push("/");
        },
        onSubmit:function(){
            //使用axios发起post请求时，第一个参数为url，第二个参数为对象
            this.$http.put("/movies/edit/" + this.movie.id,this.movie).then(response=>{
                if(response.data.status == "success"){
                    this.$message({
                       message: '编辑成功',
                       type: 'success'
                    });
                    
                    this.$router.push("/");
                }else{
                    this.$message.error(response.data.message);
                }
            }).catch(error=>{
                this.$message.error("系统提示：" + error.message);
            });
        }
    },
    //钩子函数用于回显数据，在页面加载时异步加载数据
    mounted:function() {
        var id = this.$route.params.id;
        console.log(id);
        
        this.$http.get("/movies/" + id).then(response=>{
            this.movie = response.data.result;
        }).catch(error=>{
           //使用elementUI库
           this.$message.error("系统提示：" + error.message);
        });
    }
}    
</script>

<style lang="less" scoped>
    
</style>