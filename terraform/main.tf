terraform {
  required_version = ">= 0.15.3"
}

provider "aws" {
  region = "us-east-1"
}

resource "aws_s3_bucket" "spring-playground-bucket" {
  bucket = "spring-playground-bucket"
}

resource "aws_s3_bucket_acl" "acl1" {
  bucket = aws_s3_bucket.spring-playground-bucket.id
  acl    = "private"
}