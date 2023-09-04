from flask import Flask, redirect, url_for, render_template, request, jsonify
import requests

from guhfrontend.landingpage.landingcontroller import landingPage
from guhfrontend.worker.workercontroller import workerPage

app = Flask(__name__)
app.register_blueprint(landingPage, url_prefix='/landingPage')
app.register_blueprint(workerPage, url_prefix='/workerPage')

@app.route('/')
def index():
    return redirect(url_for('landingPage.landing'))


if __name__ == '__main__':
    app.run(debug=True)
