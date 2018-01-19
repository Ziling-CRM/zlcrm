<template>
    <div>
        <Card style="margin-bottom: 20px">
            <p slot="title">
                <Icon type="ios-keypad"></Icon>
                基本信息
            </p>
            <Form :model="baseForm" :label-width="80">
                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="姓名">
                        <Input v-model="baseForm.realname" placeholder="姓名"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="工号">
                        <Input v-model="baseForm.userId" placeholder="工号(userId)"></Input>
                    </FormItem>
                    </Col>
                </Row>

                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="性别">
                        <Select v-model="baseForm.gender">
                            <Option value="m">男</Option>
                            <Option value="w">女</Option>
                        </Select>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="电话">
                        <Input v-model="baseForm.telephone" placeholder="电话"></Input>
                    </FormItem>
                    </Col>
                </Row>
                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="邮箱">
                        <Input v-model="baseForm.email" placeholder="邮箱"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="QQ">
                        <Input v-model="baseForm.qq" placeholder="QQ"></Input>
                    </FormItem>
                    </Col>
                </Row>
                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="银行卡号">
                        <Input v-model="baseForm.creditCard" placeholder="银行卡号"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="银行地址">
                        <Input v-model="baseForm.creditAddress" placeholder="银行地址"></Input>
                    </FormItem>
                    </Col>
                </Row>

                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="支付宝">
                        <Input v-model="baseForm.alipay" placeholder="支付宝"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="所在地">
                        <Input v-model="baseForm.address" placeholder="所在地"></Input>
                    </FormItem>
                    </Col>
                </Row>
            </Form>
            <Button type="primary" @click="addBaseInfo">保存基本信息</Button>
        </Card>
        <Card style="margin-bottom: 20px">
            <p slot="title">
                <Icon type="ios-keypad"></Icon>
                职能信息
            </p>
            <Form :model="skillForm" :label-width="80">
                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="技能点">
                        <Input v-model="skillForm.skill" placeholder="技能点"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="能力等级">
                        <Input v-model="skillForm.capacityRate" placeholder="能力等级"></Input>
                    </FormItem>
                    </Col>
                </Row>

                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="经验年限">
                        <Input v-model="skillForm.workYears" placeholder="经验年限"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="信用等级">
                        <Input v-model="skillForm.creditRate" placeholder="信用等级"></Input>
                    </FormItem>
                    </Col>
                </Row>
                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="职务">
                        <Input v-model="skillForm.post" placeholder="职务"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="重复雇佣率">
                        <Input v-model="skillForm.hireRate" placeholder="重复雇佣率"></Input>
                    </FormItem>
                    </Col>
                </Row>

                <Row :gutter="2">
                    <Col span="11">
                    <FormItem label="所在公司">
                        <Input v-model="skillForm.company" placeholder="所在公司"></Input>
                    </FormItem>
                    </Col>
                    <Col span="11">
                    <FormItem label="验收率">
                        <Input v-model="skillForm.checkRate" placeholder="验收率"></Input>
                    </FormItem>
                    </Col>
                </Row>

                <Row>
                    <Col span="6">
                    <FormItem label="已验收">
                        <Input v-model="skillForm.checkNum" placeholder="已验收"></Input>
                    </FormItem>
                    </Col>
                    <Col span="6">
                    <FormItem label="进展中">
                        <Input v-model="skillForm.proceedNum" placeholder="进展中"></Input>
                    </FormItem>
                    </Col>
                    <Col span="6">
                    <FormItem label="总收入">
                        <Input v-model="skillForm.userIncome" placeholder="总收入"></Input>
                    </FormItem>
                    </Col>
                    <Col span="6">
                    <FormItem label="月均收入">
                        <Input v-model="skillForm.monthIncome" placeholder="月均收入"></Input>
                    </FormItem>
                    </Col>

                </Row>
            </Form>
            <Button type="primary" @click="addProfessionalInfo">保存职能信息</Button>

        </Card>
        <!--<Card style="margin-bottom: 20px">-->
        <!--<p slot="title">-->
        <!--<Icon type="ios-keypad"></Icon>-->
        <!--项目统计-->
        <!--</p>-->
        <!--<Form>-->
        <!--<Row>-->
        <!--<Col span="6">-->
        <!--<FormItem label="已验收">-->
        <!--<Input v-model="projectForm.checkNum" placeholder="已验收"></Input>-->
        <!--</FormItem>-->
        <!--</Col>-->
        <!--<Col span="6">-->
        <!--<FormItem label="进展中">-->
        <!--<Input v-model="projectForm.checkNum" placeholder="进展中"></Input>-->
        <!--</FormItem>-->
        <!--</Col>-->
        <!--<Col span="6">-->
        <!--<FormItem label="总收入">-->
        <!--<Input v-model="projectForm.checkNum" placeholder="总收入"></Input>-->
        <!--</FormItem>-->
        <!--</Col>-->
        <!--<Col span="6">-->
        <!--<FormItem label="月均收入">-->
        <!--<Input v-model="projectForm.checkNum" placeholder="月均收入"></Input>-->
        <!--</FormItem>-->
        <!--</Col>-->

        <!--</Row>-->
        <!--</Form>-->

        <!--<Button  style="margin-bottom: 20px" type="primary" shape="circle" @click="addProjectDialog = true">添加项目</Button>-->
        <!--<br>-->
        <!--<Card style="width: 400px;">-->
        <!--<project-card></project-card>-->
        <!--&lt;!&ndash;<Button type="info" @click="editProject">修改</Button>&ndash;&gt;-->

        <!--</Card>-->


        <!--<Modal :width="400" v-model="addProjectDialog" @on-ok="addProject" @on-cancel="cancelAddProject">-->

        <!--<project-card :editable="true"></project-card>-->
        <!--<div slot="footer">-->
        <!--&lt;!&ndash;<Button type="error" size="large" long >Delete</Button>&ndash;&gt;-->
        <!--</div>-->
        <!--</Modal>-->

        <!--<Button type="primary" style="margin-top: 20px">保存项目统计</Button>-->

        <!--</Card>-->
    </div>
</template>
<script>
    import ProjectCard from './project_card'
    import API from '../../api'

    export default {
        data() {
            return {
                projectForm: {
                    checkNum: ''
                },
                baseForm: {
                    userId: '',
                    realname: '',
                    gender: '',
                    telephone: '',
                    qq: '',
                    email: '',
                    creditCard: '',
                    creditAddress: '',
                    alipay: '',
                    address: '',
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
                addProjectDialog: false

            }
        },
        components: {ProjectCard},
        methods: {
            addBaseInfo() {
                API('addBaseInfo', this.baseForm).then(res => {
                    console.log(res)
                    if (res.code === 0) {
                        this.$Message.success(res.msg);
                    } else{
                        this.$Message.error(res.msg);
                    }
                })
            },
            addProfessionalInfo() {
                API('addProfessionalInfo', {...this.skillForm, userId: this.baseForm.userId}).then(res => {
                    console.log(res)
                    if (res.code === 0) {
                        this.$Message.success(res.msg);
                    } else{
                        this.$Message.error(res.msg);
                    }
                })

            },

        },
        computed: {},
        mounted() {
        }
    }
</script>
<style scoped lang="less">

</style>
