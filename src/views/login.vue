<style lang="less">
    @import './login.less';
</style>

<template>
    <div class="login" @keydown.enter="handleSubmit">
        <div class="login-con">
            <Card :bordered="false">
                <p slot="title">
                    <Icon type="log-in"></Icon>
                    欢迎登录
                </p>
                <div class="form-con">
                    <Form ref="loginForm" :model="form" :rules="rules">
                        <FormItem prop="userName">
                            <Input v-model="form.userName" placeholder="请输入用户名">
                            <span slot="prepend">
                                    <Icon :size="16" type="person"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem prop="password">
                            <Input type="password" v-model="form.password" placeholder="请输入密码">
                            <span slot="prepend">
                                    <Icon :size="14" type="locked"></Icon>
                                </span>
                            </Input>
                        </FormItem>
                        <FormItem>
                            <Button @click="handleSubmit" type="primary" long>登录</Button>
                        </FormItem>
                    </Form>
                    <p class="login-tip">输入任意用户名和密码即可</p>
                </div>
            </Card>
        </div>
    </div>
</template>

<script>
    import Cookies from 'js-cookie';
    import API from '../api'

    export default {
        data() {


            return {
                form: {
                    userName: 'sadmin',
                    password: 'sadmin'
                },
                rules: {
                    userName: [
                        {required: true, message: '账号不能为空', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '密码不能为空', trigger: 'blur'}
                    ],
                }
            };
        },
        methods: {
            handleSubmit() {
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        console.log('login')

                        API('login', {
                            username: this.form.userName,
                            password: this.form.password
                        }).then(res => {
                            if (res.code === 0) {
                                console.log(res)
                                this.$store.commit('setLoginUser', res.data)

                                Cookies.set('user', this.form.userName);
                                Cookies.set('password', this.form.password);

                                Cookies.set('access', res.data.permission);

                                this.$router.push({
                                    name: 'home_index'
                                });

                            } else {
                                this.$Message.error(res.msg);
                            }
                        })


                    }
                });
            }
        }
    };
</script>

<style>

</style>
