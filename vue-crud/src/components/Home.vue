<template>
    <div id="home">
        <el-button id="add" type="primary" round @click="addmovie">新增电影</el-button>
        <el-table  :data="movies" stripe style="width: 100%;margin-top:20px">
            <el-table-column
                prop="title"
                label="电影名称"
                width="180">
            </el-table-column>
            <el-table-column
                prop="rate"
                label="评分"
                width="180">
            </el-table-column>
            <el-table-column
                prop="releaseYear"
                label="发行时间"
                width="100">
            </el-table-column>
            <el-table-column
                prop="sendTime"
                label="上映时间"
                width="100">
            </el-table-column>
            <el-table-column
                prop="director"
                label="导演">
            </el-table-column>
            <el-table-column label="操作" width="140">
                <template slot-scope="scope">
                    <el-button type="primary" icon="el-icon-edit" circle  @click="handleEdit(scope.$index, scope.row)"></el-button>
                    <el-button type="danger" icon="el-icon-delete" circle  @click="handleDelete(scope.$index, scope.row)"></el-button>
                </template>
            </el-table-column> 
        </el-table> 
        <!-- page-size每页显示条目个数 layout组件布局 -->
        <!-- current-page当前页数  .sync修饰符，可以实现父组件和子组件之间的双向绑定 -->
        <el-pagination  background
            @current-change="handleCurrentChange"
            :current-page="currentPage"
            :page-size="10"
            layout="prev, pager, next,total"
            :total=total>
        </el-pagination>
    </div>
</template>

<script>
//导入url常量
import  api from '../const/url'

export default {
    name:"Home",
    data(){
        return {
            movies:[],
            total:0,
            currentPage:1
        }
    },
    methods:{
        addmovie:function(){
            //跳转到新增页面 使用编程式导航的方式
            this.$router.push("/new");
        },
        handleEdit:function(index,row){
            var id = row.id;
            this.$router.push("/edit/" + id);
        },
        handleDelete:function(index,row){
            var id = row.id;
            this.$confirm("确定要删除该电影吗？").then(()=>{
                this.$http.delete("/movies/del/" + id).then(response=>{
                    if(response.data.status == "success"){
                        this.$message.success("删除成功");
                        //根据索引将数组中的内容删除
                        this.movies.splice(index,1);
                    }
                }).catch(error=>{
                    this.$message.error("系统提示：" + error.message);
                });
            }).catch(()=>{});
        },
        //当前页改变时触发
        handleCurrentChange:function(page){
            this.currentPage = page;
            this.loadData(this.currentPage);
        },
        loadData:function(pageNo){
             //在运行时就会自动加载baseURL
            //发起请求，使用axios库
           var pageNo = pageNo | 1;
            this.$http.get(api.Home + "/?p=" + pageNo).then(response=>{
            this.movies = response.data.result.list;
            this.total = response.data.result.total;    
            }).catch(error=>{
           //使用elementUI库
           this.$message.error("系统提示：" + error.message);
            });
        }
    },
    mounted:function() {
      this.loadData(this.currentPage);
    }
}
</script>

<style lang="less" scoped>
     
</style>
