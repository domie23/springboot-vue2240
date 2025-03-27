const base = {
    get() {
        return {
            url : "http://localhost:8080/xiaoyuanxuanshang/",
            name: "xiaoyuanxuanshang",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/xiaoyuanxuanshang/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "校园悬赏任务平台"
        } 
    }
}
export default base
