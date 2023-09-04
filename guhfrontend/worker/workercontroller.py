from flask import Flask, render_template, request, redirect, url_for, Blueprint

workerPage = Blueprint('workerPage', __name__, template_folder='templates', static_folder='static')


@workerPage.route('/')
@workerPage.route('/home/')
def home():
    return render_template('worker_page.html')

@workerPage.route('/userlogin/')
def userlogin():
    return render_template('worker_userlogin.html')

@workerPage.route('/registeruser/')
def registeruser():
    return render_template('worker_registeruser.html')