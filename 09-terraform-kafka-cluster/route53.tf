resource "aws_route53_zone" "private" {
  name = "arviu.in"

  vpc {
     vpc_id = "${var.vpc_id}"
  }
}

resource "aws_route53_record" "arivu-route-53-record-1" {
  zone_id = aws_route53_zone.private.zone_id
  name    = "node1.arviu.in"
  type    = "A"
  ttl     = "300"
  records = [aws_instance.arivu-aws-kafka.private_ip]
}

resource "aws_route53_record" "arivu-route-53-record-2" {
  zone_id = aws_route53_zone.private.zone_id
  name    = "node2.arviu.in"
  type    = "A"
  ttl     = "300"
  records = [aws_instance.arivu-aws-kafka-2.private_ip]
}

resource "aws_route53_record" "arivu-route-53-record-3" {
  zone_id = aws_route53_zone.private.zone_id
  name    = "node3.arviu.in"
  type    = "A"
  ttl     = "300"
  records = [aws_instance.arivu-aws-kafka-3.private_ip]
}
