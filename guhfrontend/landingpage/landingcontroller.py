from flask import Flask, render_template, request, redirect, url_for, Blueprint

landingPage = Blueprint('landingPage', __name__, template_folder='templates', static_folder='static')


@landingPage.route('/')
@landingPage.route('/home/')
def landing():
    return render_template('landingpage.html')


@landingPage.route('/login/')
def login():
    return render_template('login.html')


@landingPage.route('/register/')
def register():
    return render_template('register.html')