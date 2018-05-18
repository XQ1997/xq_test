<template>
    <div id="add">
        <el-button plain @click="back">返回列表</el-button>
        <el-form style="margin-top:30px" ref="form" :model="movie" label-width="80px" size="mini">
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
                <el-button type="primary" @click="onSubmit">立即创建</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
import api from '../const/url';

export default{
    name : 'add',
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
            this.$http.post(api.add,this.movie).then(response=>{
                if(response.data.status == "success"){
                    this.$message({
                       message: '新增成功',
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
    }
}    
</script>

<style lang="less" scoped>
    
</style>