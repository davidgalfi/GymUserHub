from flask import Flask, render_template, request, redirect, url_for, Blueprint

workerPage = Blueprint('workerPage', __name__, template_folder='templates', static_folder='static')


@workerPage.route('/')
@workerPage.route('/home')
def home():
    return render_template('worker_page.html')