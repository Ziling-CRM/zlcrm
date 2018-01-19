<template>
    <div>
        <Row class="margin-top-10">
            <Col span="24">
            <Card>
                <p slot="title">
                    <Icon type="ios-keypad"></Icon>
                    管理员列表
                </p>
                <Row :gutter="10">
                    <!--<Col span="2">-->
                    <!--<Row type="flex" justify="center" align="middle" class="edittable-table-get-currentdata-con">-->
                    <!--<Button type="primary" @click="getCurrentData">当前数据</Button>-->
                    <!--</Row>-->
                    <!--</Col>-->
                    <Col span="24">
                    <div class="edittable-table-height-con">
                        <can-edit-table v-if="adminLists"
                                        v-model="adminLists"
                                        @on-change="handleChange"
                                        @on-delete="handleDelete"
                                        :columns-list="tableHeader"
                        ></can-edit-table>
                    </div>
                    </Col>
                    <!--<Modal :width="900" v-model="showCurrentTableData">-->
                    <!--<can-edit-table refs="table5" v-model="editInlineAndCellData"-->
                    <!--:columns-list="showCurrentColumns"></can-edit-table>-->
                    <!--</Modal>-->
                </Row>
                <Button type="primary" @click="addDialog = true">添加管理员</Button>
                <br>
                <h2></h2>
                超级管理员, 对应值: * "所有权限",无法通过本页面添加<br>
                一级管理员, 对应值: 0 "所有权限",<br>
                二级管理员, 对应值: 1 "不能删除",<br>
                三级管理员, 对应值: 2 "不能删除或者修改", <br>
                四级管理员, 对应值: 3 "只能查看";
                <Modal :width="400" v-model="addDialog" @on-ok="add">
                    <Form :model="formItem" :label-width="80">
                        <FormItem label="用户名">
                            <Input v-model="formItem.username" placeholder="用户名"></Input>
                        </FormItem>

                        <FormItem label="工号">
                            <Input v-model="formItem.userId" placeholder="工号"></Input>
                        </FormItem>
                        <FormItem label="密码">
                            <Input v-model="formItem.password" placeholder="密码"></Input>
                        </FormItem>


                        <FormItem label="权限">
                            <Select v-model="formItem.permission">
                                <Option value="0">一级管理员</Option>
                                <Option value="1">二级管理员</Option>
                                <Option value="2">三级管理员</Option>
                                <Option value="3">四级管理员</Option>
                            </Select>
                        </FormItem>

                    </Form>
                </Modal>

            </Card>
            </Col>

        </Row>
    </div>
</template>
<script>
    import canEditTable from './components/canEditTable.vue';
    import tableData from './components/table_data.js';
    import API from '../../api'
    import {mapState} from 'vuex'

    export default {
        data() {
            return {
                editInlineAndCellData: [
                    {
                        name: 'Aresn',
                        sex: '男',
                        work: '前端开发'
                    },
                    {
                        name: 'Lison',
                        sex: '男',
                        work: '前端开发'
                    },
                    {
                        name: 'lisa',
                        sex: '女',
                        work: '程序员鼓励师'
                    }
                ],
                tableHeader: [
                    {
                        title: '序号',
                        type: 'index',
                        width: 80,
                        align: 'center'
                    },
                    {
                        title: '工号',
                        align: 'center',
                        key: 'userId',
                        editable: true
                    },
                    {
                        title: '用户名',
                        align: 'center',
                        key: 'username',
                        editable: true

                    },
                    {
                        title: '密码',
                        align: 'center',
                        key: 'password',
                        editable: true
                    },
                    {
                        title: '权限,请填写0，1，2，3',
                        align: 'left',
                        width: 300,
                        key: 'permission',
                        editable: true
                    },
                    {
                        title: '操作',
                        align: 'center',
                        width: 200,
                        key: 'handle',
                        handle: ['edit', 'delete']
                    }
                ],
                showCurrentTableData: false,
                addDialog: false,
                password: '',
                formItem: {
                    username: null,
                    permission: null,
                    userId: null,
                    password: null
                },
                adminLists: []


                // showCurrentColumns: [],

            }
        },
        created() {
            this.fetchData()
            // console.log(this.editInlineAndCellData)
        },
        components: {canEditTable},
        methods: {
            fetchData() {
                console.log('fetch')
                API('listAllAdmin', {
                    username: 'sadmin'
                }).then(res => {
                    if (res.code === 0) {
                        this.adminLists = res.data.adminLists
                    }
                })
            },
            handleDelete(index) {
                API('deleteAdmin', {
                    userId: this.adminLists[index].userId
                }).then(res => {
                    console.log(res)
                    if (res.code === 0) {
                        this.adminLists.splice(index, 1);
                        this.$Message.success('成功删除管理员');
                    } else {
                        this.$Message.error(res.msg);
                    }
                })
                console.log(this.adminLists)
            },
            add() {
                console.log(this.formItem)
                API('addAdmin', this.formItem).then(res => {
                    console.log(res)
                    if (res.code === 0) {
                        this.$Message.success('成功添加管理员');
                        this.formItem = {}
                        this.fetchData()
                    } else {
                        this.$Message.error(res.msg);
                    }
                })

            },
            handleChange(val, index, key) {
                let {userId, username, password, permission} = this.adminLists[index]


                API('updateAdmin', {
                    userId, username, password, permission
                }).then(res => {
                    console.log(res)
                    if (res.code === 0) {
                        this.$Message.success('修改成功！');
                        this.fetchData()
                    } else {
                        this.$Message.error(res.msg);
                    }

                })

                // this.$Message.success('修改了第 ' + (index + 1) + ' 行列名为 ' + key + ' 的数据');
            },
            // handleChange(val, index) {
            //     this.$Message.success('修改了第' + (index + 1) + '行数据');
            // }
        },
        computed: {},
        mounted() {
        }
    }
</script>
<style scoped lang="less">

</style>
