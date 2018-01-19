<template>
    <div>
        <Row>
            <Col span="24">
            <Card>
                <p slot="title">
                    <Icon type="mouse"></Icon>
                    点击搜索进行搜索

                </p>


                <Row>
                    <Input v-model="searchF.userId" placeholder="工号" style="width: 200px"/>
                    <Input v-model="searchF.realname" placeholder="用户名" style="width: 200px"/>
                    <Input v-model="searchF.duty" placeholder="职务" style="width: 200px"/>

                    <span @click="search" style="margin: 0 10px;"><Button type="primary" icon="search" @click="fetchData">搜索</Button></span>
                    <!--<Button @click="cancel" type="ghost">取消</Button>-->
                    <!--<RadioGroup v-model="showFilter" type="button" @on-change="changeFilter">-->
                        <!--<Radio label="全部"></Radio>-->

                        <!--<Radio label="按职务"></Radio>-->
                        <!--<Radio label="按客户工号"></Radio>-->
                        <!--<Radio label="按客户真实姓名"></Radio>-->
                    <!--</RadioGroup>-->
                </Row>
                <Row style="margin: 20px 0;">
                    <Table border :columns="columns1" :data="userList"></Table>
                </Row>

                <Page :current="2" :total="50" simple></Page>
                <div style="margin: 20px 0">
                    <Button type="ghost">修改</Button>
                    <Button type="primary">保存</Button>
                </div>


            </Card>
            </Col>
        </Row>
    </div>
</template>
<script>
    import * as table from './data';
    import API from '../../api'

    export default {
        data() {
            return {
                searchF: {

                },
                searchInfo: null,
                columns1: [
                    {
                        title: '序号',
                        key: 'index',
                        type: 'index',
                        width: 80
                    },
                    {
                        key: 'userId',
                        title: '工号',

                    },
                    {
                        key: 'realname',
                        title: '客户名'
                    },
                    {
                        key: 'post',
                        title: '职务',

                    },
                    {
                        key: 'capacityRate',
                        title: '能力等级'
                    },
                    {
                        key: 'creditRate',
                        title: '信用等级'
                    },
                    {
                        title: '操作',
                        key: 'action',
                        fixed: 'right',
                        width: 120,
                        render: (h, params) => {
                            return h('div', [
                                h('Button', {
                                    props: {
                                        type: 'text',
                                        size: 'small'
                                    },
                                    on: {
                                        'click': () => this.toDetail(params)
                                    }
                                }, '查看详情')
                            ]);
                        }
                    }
                ],
                userList: [],
                filter: '',
                showFilter: '全部'


            }
        },
        created() {
            this.data3 = this.initTable3 = table.searchTable3;
            this.fetchData()
        },
        components: {},
        methods: {
            changeFilter(val) {
                switch (val) {
                    case '按职务':
                        this.filter = 'duty'
                        break
                    case '按客户工号':
                        this.filter = 'userId'
                        break
                    case '按客户真实姓名':
                        this.filter = 'realname'
                        break
                    case '全部':
                        this.filter = 'all'
                        break
                }
                this.fetchData()
            },
            fetchData() {

                API('listAllUser', {
                    ...this.searchF
                }).then(res => {
                    console.log(res)
                    if (res.code === 0) {
                        this.userList = res.data.userList

                    }else{
                        this.$Message.error(res.msg)
                    }
                })
            },
            toDetail(params) {
                this.$router.push({
                    name: 'userInfo',
                    params: {id: params.row.userId}
                })
            },
            search() {

            },
            cancel() {

            },
            handleSearch3() {
                this.data3 = this.initTable3;
                this.data3 = this.search(this.data3, {name: this.searchConName3});
            },
            handleCancel3() {
                this.data3 = this.initTable3;
            }
        },
        computed: {},
        mounted() {
        }
    }
</script>
<style scoped lang="less">

</style>
