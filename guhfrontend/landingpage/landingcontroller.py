import requests
from flask import Flask, render_template, request, redirect, url_for, Blueprint

landingPage = Blueprint('landingPage', __name__, template_folder='templates', static_folder='static')


@landingPage.route('/')
@landingPage.route('/home/')
def landing():
    return render_template('landingpage.html')


@landingPage.route('/login/', methods=['GET', 'POST'])
def login():
    if request.method == 'GET':
        return render_template('login.html')
    elif request.method == 'POST':
        worker_email = request.form.get('worker_email')
        worker_password = request.form.get('worker_password')

        worker_datas = {
            'email': worker_email,
            'password': worker_password
        }

        response = requests.post('http://localhost:8080/api/gymworker/login', json=worker_datas)

        if response.status_code == 200:
            return redirect(url_for('workerPage.home'))
        else:
            return redirect(url_for('landingPage.login'))


@landingPage.route('/register/')
def register():
    return render_template('register.html')