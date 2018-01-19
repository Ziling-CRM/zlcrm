<template>
    <div>
        <Card>
            <p slot="title">
                <Icon type="ios-keypad"></Icon>
                基本信息
            </p>
            <Form :model="baseForm" :label-width="80" :rule="baseFormRole">
                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="姓名">
                        <Input v-model="baseForm.realname" placeholder="姓名" :disabled="baseFormDisabled"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="工号">
                        <Input v-model="baseForm.userId" placeholder="工号" :disabled="baseFormDisabled"></Input>
                    </FormItem>
                    </Col>
                </Row>

                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="性别">
                        <Select v-model="baseForm.gender" :disabled="baseFormDisabled">
                            <Option value="m">男</Option>
                            <Option value="w">女</Option>
                        </Select>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="电话">
                        <Input v-model="baseForm.telephone" placeholder="电话" :disabled="baseFormDisabled"></Input>
                    </FormItem>
                    </Col>
                </Row>
                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="邮箱">
                        <Input v-model="baseForm.email" placeholder="邮箱" :disabled="baseFormDisabled"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="QQ">
                        <Input v-model="baseForm.qq" placeholder="QQ" :disabled="baseFormDisabled"></Input>
                    </FormItem>
                    </Col>
                </Row>


                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="银行卡号">
                        <Input v-model="baseForm.creditcard" placeholder="银行卡号" :disabled="baseFormDisabled"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="银行地址">
                        <Input v-model="baseForm.creditaddress" placeholder="银行地址" :disabled="baseFormDisabled"></Input>
                    </FormItem>
                    </Col>
                </Row>


                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="支付宝">
                        <Input v-model="baseForm.alipay" placeholder="支付宝" :disabled="baseFormDisabled"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="所在地">
                        <Input v-model="baseForm.address" placeholder="所在地" :disabled="baseFormDisabled"></Input>
                    </FormItem>
                    </Col>
                </Row>
            </Form>
            <Button @click="baseFormDisabled = false">修改</Button>
            <Button type="primary" @click="saveBase" :disabled="baseFormDisabled">保存</Button>

        </Card>
        <Card>
            <p slot="title">
                <Icon type="ios-keypad"></Icon>
                职能信息
            </p>
            <Form :model="skillForm" :label-width="80">
                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="技能点">
                        <Input v-model="skillForm.skill" placeholder="技能点" :disabled="skillFormDisabled"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="能力等级">
                        <Input v-model="skillForm.capacityRate" placeholder="能力等级"
                               :disabled="skillFormDisabled"></Input>
                    </FormItem>
                    </Col>
                </Row>

                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="经验年限">
                        <Input v-model="skillForm.workYears" placeholder="经验年限" :disabled="skillFormDisabled"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="信用等级">
                        <Input v-model="skillForm.creditRate" placeholder="信用等级" :disabled="skillFormDisabled"></Input>
                    </FormItem>
                    </Col>
                </Row>
                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="职务">
                        <Input v-model="skillForm.post" placeholder="职务" :disabled="skillFormDisabled"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="重复雇佣率">
                        <Input v-model="skillForm.hireRate" placeholder="重复雇佣率" :disabled="skillFormDisabled"></Input>
                    </FormItem>
                    </Col>
                </Row>

                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="所在公司">
                        <Input v-model="skillForm.company" placeholder="所在公司" :disabled="skillFormDisabled"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="验收率">
                        <Input v-model="skillForm.checkRate" placeholder="验收率" :disabled="skillFormDisabled"></Input>
                    </FormItem>
                    </Col>
                </Row>

                <Row>
                    <Col span="6">
                    <FormItem label="已验收">
                        <Input v-model="skillForm.checkNum" placeholder="已验收" :disabled="skillFormDisabled"></Input>
                    </FormItem>
                    </Col>
                    <Col span="6">
                    <FormItem label="进展中">
                        <Input v-model="skillForm.proceedNum" placeholder="进展中" :disabled="skillFormDisabled"></Input>
                    </FormItem>
                    </Col>
                    <Col span="6">
                    <FormItem label="总收入">
                        <Input v-model="skillForm.userIncome" placeholder="总收入" :disabled="skillFormDisabled"></Input>
                    </FormItem>
                    </Col>
                    <Col span="6">
                    <FormItem label="月均收入">
                        <Input v-model="skillForm.monthIncome" placeholder="月均收入" :disabled="skillFormDisabled"></Input>
                    </FormItem>
                    </Col>

                </Row>

            </Form>
            <Button @click="skillFormDisabled = false">修改</Button>
            <Button type="primary" @click="saveDuty" :disabled="skillFormDisabled">保存</Button>
        </Card>
        <Card>
            <p slot="title">
                <Icon type="ios-keypad"></Icon>
                项目统计
            </p>
            <Tabs value="name1">
                <TabPane label="已验收" name="name1">
                    <h3>点击卡片进行修改或者删除</h3>
                    <Button style="margin-bottom: 20px" type="primary" shape="circle" @click="() => openModal('add')">添加项目
                    </Button>
                    <br>
                    <div style="display: flex;flex-wrap: wrap">
                        <project-card v-if="projectslist" v-for="item,index in projectslist" :key="index"
                                      :info="item" :onEdit="() => openModal('edit',index)"></project-card>
                    </div>
                </TabPane>
                <TabPane label="进展中" name="name2">标签二的内容</TabPane>
                <TabPane label="数据图表" name="name3">标签三的内容</TabPane>
            </Tabs>



            <!--<Button type="info" @click="editProject">修改</Button>-->


            <Modal :width="400" v-model="addProjectDialog" @on-ok="addProject">
                <p slot="header">
                    <Icon type="information-circled"></Icon>
                    <span>项目详情</span>
                </p>
                <project-card :editable="true" :userId="$route.params.id" :refresh="refresh" :editMode="editMode" :editForm="editForm"></project-card>
                <div slot="footer">
                    <!--<Button type="error" size="large" long >Delete</Button>-->
                </div>
            </Modal>

            <!--<Button type="primary" style="margin-top: 20px">保存项目统计</Button>-->

        </Card>
        </Card>
        <Modal v-model="deleteConfirm" width="360">
            <p slot="header" style="color:#f60;text-align:center">
                <Icon type="information-circled"></Icon>
                <span>确认删除用户？</span>
            </p>
            <div style="text-align:center">
                <p>这将删除用户</p>
                <p>确认请在点击下面的按钮，或者取消</p>
            </div>
            <div slot="footer">
                <Button type="error" size="large" long  @click="del">确认删除用户</Button>
            </div>
        </Modal>
        <Button type="error" long style="margin-top: 20px" size="large" @click="deleteConfirm = true">删除用户</Button>

    </div>
</template>
<script>
    import ProjectCard from './project_card'
    import API from '../../api'

    export default {
        data() {
            const emailValidator = (rule,email,cb) => {
                if(!email){
                    return cb(new Error('请输入邮箱'))
                } else if(/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/.test(email)){
                    return cb(new Error('请输入的正确的邮箱格式'))
                } else{
                    cb()
                }
            }

            const telephoneValidor = (rule,mobile,cb)=> {
                if(!mobile){
                    return cb(new Error('请输入手机号码'))
                } else if(mobile.length === 11){
                    return cb(new Error('手机号码为11位，请确认'))
                } else{
                    cb()
                }
            }

            return {
                editMode: false,
                baseFormDisabled: true,
                skillFormDisabled: true,
                baseForm: {
                    realname: '',
                    userId: '',
                    gender: '',
                    telephone: '',
                    qq: '',
                    email: '',
                    alipay: '',
                    location: ''
                },
                baseFormRole: {
                    email: [{validator: emailValidator,trigger: 'blur'}],
                    telephoneValidor: [{validator: telephoneValidor,trigger: 'blur'}]
                },
                skillForm: {
                    skill: '',
                    capacityRate: '',
                    workYears: '',
                    creditRate: '',
                    post: '',
                    hireRate: '',
                    company: '',
                    checkRate: '',
                    checkNum: '',
                    proceedNum: '',
                    userIncome: '',
                    monthIncome: ''
                },
                skillFormRule: {

                },
                addProjectDialog: false,
                projectslist: [],
                deleteConfirm: false,
                editForm: null

            }
        },


        components: {ProjectCard},
        created() {
            this.refresh()

        },
        methods: {
            saveBase () {
                this.$refs.baseFormRole.validate((valid) => {
                    if (valid) {
                        API('updateUserBase',{
                            ...this.baseForm
                        }).then(res => {
                            if(res.code === 0){
                                this.baseFormDisabled = true
                                this.$Message.success(res.msg)
                            }else {
                                this.$Message.error(res.msg)

                            }

                        })
                    }
                })
            },
            saveDuty(){
                API('updateUserDuty',{
                    ...this.skillForm,
                    userId: this.$route.params.id
                }).then(res => {
                    if(res.code === 0){
                        this.skillFormDisabled = true
                        this.$Message.success(res.msg)
                    }else {
                        this.$Message.error(res.msg)

                    }

                })
            },
            openModal(f,i) {
                if(f === 'edit'){
                    this.editMode = true
                    this.editForm = this.projectslist[i]
                    setTimeout(() => this.addProjectDialog = true, 300)
                }else{
                    this.editMode = false
                    this.editForm = null
                    this.addProjectDialog = true

                }
            },
            del () {
                API('deleteUser', {
                    userId: this.$route.params.id
                }).then(res => {
                    console.log(res)
                    if(res.code === 0){
                        this.$router.push({
                            name: 'user_search'
                        })
                    }

                }).catch(err => {
                    this.deleteConfirm  = false
                    this.$Message.error('网络错误')
                })
            },
            refresh() {
                API('getUserInfo', {
                    userId: this.$route.params.id
                }).then(res => {
                    console.log(res)
                    this.projectslist = res.data.projectsList
                    this.baseForm = res.data.baseUser
                    this.skillForm = res.data.duty[0]



                })
                this.addProjectDialog = false
            },

            addProject() {

            },
            cancelAddProject() {

            }
        },
        computed: {},
        mounted() {
        }
    }
</script>
<style scoped lang="less">

</style>
