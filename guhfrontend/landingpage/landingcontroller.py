from flask import Flask, render_template, request, redirect, url_for, Blueprint

landingPage = Blueprint('landingPage', __name__, template_folder='templates', static_folder='static')

