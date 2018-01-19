<style scoped>

</style>
<template>
    <div style="cursor: pointer">
        <Form v-if="editable">
            <FormItem label="项目名称">
                <Input v-model="projectForm.projectName" placeholder="项目名称"></Input>
            </FormItem>
            <FormItem label="项目类型">
                <Input v-model="projectForm.projectType" placeholder="项目类型"></Input>
            </FormItem>
            <FormItem label="项目总价格">
                <Input v-model="projectForm.projectPrice" placeholder="项目总价格"></Input>
            </FormItem>
            <FormItem label="项目收入">
                <Input v-model="projectForm.projectIncome" placeholder="项目收入"></Input>
            </FormItem>
            <FormItem label="项目状态">
                <Input v-model="projectForm.projectState" placeholder="项目状态"></Input>
            </FormItem>

            <Button type="info" @click="addUserProject">保存项目</Button>
        </Form>
        <div v-else-if="projectForm" @click="editit">
            <Card style="width: 300px; margin: 10px 20px 10px 0">
                <Form>
                    <FormItem label="项目名称">{{projectForm.projectName}}</FormItem>
                    <FormItem label="项目类型">
                        {{projectForm.projectType}}
                    </FormItem>
                    <FormItem label="项目总价格">
                        {{projectForm.projectPrice}}
                    </FormItem>
                    <FormItem label="项目收入">
                        {{projectForm.projectIncome}}
                    </FormItem>
                    <FormItem label="项目状态">
                        {{projectForm.projectState}}
                    </FormItem>

                </Form>
            </Card>

        </div>


    </div>
</template>
<script>
    import API from '../../api'

    export default {
        data() {
            return {
                projectForm: {
                    projectName: null,
                    projectType: null,
                    projectPrice: null,
                    projectIncome: null,
                    projectState: null
                },
                init: {
                    projectName: null,
                    projectType: null,
                    projectPrice: null,
                    projectIncome: null,
                    projectState: null
                },

            }
        },
        name: 'project-card',
        // props: ['pojectName', 'projectType', 'pojectPrice', 'pojectIncome', 'pojectState'],
        props: ['editable', 'userId', 'refresh', 'info', 'refresh', 'onEdit', 'proId', 'editMode','editForm'],
        components: {},
        methods: {
            editit() {
                this.onEdit()
            },
            submitUpdate() {
                API('updateUserProject', {
                    ...this.projectForm,
                    userId: this.userId
                }).then(res => {
                    if (res.code === 0) {
                        this.refresh()
                        this.$Message.success(res.msg);
                    }
                    else {
                        this.$Message.error(res.msg);
                    }

                })
            },
            addUserProject() {
                if (this.editMode) {
                    API('updateUserProject', {
                        ...this.projectForm,
                        userId: this.userId
                    }).then(res => {
                        if (res.code === 0) {
                            this.refresh()
                            this.$Message.success(res.msg);
                        }
                        else {
                            this.$Message.error(res.msg);
                        }

                    })

                } else {
                    API('addUserProject', {
                        ...this.projectForm,
                        userId: this.userId
                    }).then(res => {
                        if (res.code === 0) {
                            this.refresh()
                            this.projectForm = this.init

                            this.$Message.success(res.msg);
                        }
                        else {
                            this.$Message.error(res.msg);
                        }

                    })
                }

            }
        },
        computed: {},

        mounted() {
            if (this.info) {
                this.projectForm = this.info
            }
        },
        editForm() {
            if(this.editForm){
                this.projectForm = this.editForm
            }
        },
        watch: {
            editForm: function () {
                this.projectForm = this.editForm
            }
        }
    }
</script>

