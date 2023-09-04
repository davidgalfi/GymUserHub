from flask import Flask, redirect, url_for, render_template, request, jsonify
import requests

from guhfrontend.landingpage.landingcontroller import landingPage

app = Flask(__name__)
app.register_blueprint(landingPage, url_prefix='/landingPage')


@app.route('/')
def index():
    return jsonify(requests.get('http://localhost:8080/api/gymworker/get').json())


if __name__ == '__main__':
    app.run(debug=True)
